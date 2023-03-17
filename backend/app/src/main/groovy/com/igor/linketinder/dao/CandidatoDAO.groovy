package com.igor.linketinder.dao

import com.igor.linketinder.model.Candidato
import com.igor.linketinder.model.Competencia
import com.igor.linketinder.model.TipoCompetencia
import com.igor.linketinder.dao.fabricaBanco.FabricaBanco
import groovy.sql.Sql
import groovy.transform.TypeChecked

import java.text.SimpleDateFormat

@TypeChecked
class CandidatoDAO {

    private Sql sql

    CandidatoDAO(FabricaBanco fabricaBanco) {
        sql = fabricaBanco.iniciarBancoDeDados().conectar()
    }

    static private void validaCandidato(Candidato candidato) {
        if (candidato == null) {
            throw new RuntimeException("Não foi possível encontrar um candidato com o CPF fornecido.")
        }
    }

    void salvar(Candidato candidato) {
        String nascimento = new SimpleDateFormat("yyyy-MM-dd").format(candidato.nascimento)

        sql.executeInsert('INSERT INTO candidatos ' +
                '(nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha) ' +
                "VALUES ('${candidato.nome}', '${candidato.sobrenome}', '${nascimento}', " +
                "'${candidato.email}', '${candidato.cpf}', '${candidato.pais}', '${candidato.cep}', " +
                "'${candidato.descricao}', '${candidato.senha}')")
    }

    void atualiza(Candidato candidato) {
        String nascimento = new SimpleDateFormat("yyyy-MM-dd").format(candidato.nascimento)

        sql.executeInsert('UPDATE candidatos ' +
                "SET nome = '${candidato.nome}', sobrenome = '${candidato.sobrenome}', " +
                "data_nascimento = '${nascimento}', email = '${candidato.email}', " +
                "cpf = '${candidato.cpf}', pais = '${candidato.pais}', cep = '${candidato.cep}', " +
                "descricao = '${candidato.descricao}', senha = '${candidato.senha}' " +
                "WHERE cpf = '${candidato.cpf}'")
    }

    void remove(String cpf) {
        sql.executeInsert('DELETE FROM candidatos ' +
                "WHERE cpf = '${cpf}'")
    }


    Candidato pegar(String cpf) {

        Candidato candidato = null
        sql.eachRow("""SELECT c.nome, c.sobrenome, c.data_nascimento, c.email, c.cpf,
                            c.pais, c.cep, c.descricao, c.senha, array_agg(competencia.competencia) as competencias
                            FROM candidatos c
                            INNER JOIN competencias_candidato cc ON cc.candidatos_id = c.id
                            INNER JOIN competencias competencia ON competencia.id = cc.competencia_id
                            WHERE c.cpf = ${cpf}
                            GROUP BY c.id;""") { rs ->
            List<TipoCompetencia> competenciasList = new ArrayList<>(Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(rs.getString('competencias')))
            Competencia competencia = new Competencia()
            competencia.competencias = competenciasList
            Date data = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString('data_nascimento'))

            candidato = new Candidato(rs.getString('nome'), rs.getString('sobrenome'), data, rs.getString('email'),
                    rs.getString('cpf'), rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'),
                    rs.getString('senha'), competencia)
        }
        validaCandidato(candidato)
        return candidato
    }

    List<Candidato> listaComTodosCandidatos() {
        List<Candidato> listaCandidato = new ArrayList<>()

        sql.eachRow("""SELECT c.nome, c.sobrenome, c.data_nascimento, c.email, c.cpf,
                            c.pais, c.cep, c.descricao, c.senha, array_agg(competencia.competencia) as competencias
                            FROM candidatos c
                            INNER JOIN competencias_candidato cc ON cc.candidatos_id = c.id
                            INNER JOIN competencias competencia ON competencia.id = cc.competencia_id
                            GROUP BY c.id;""") { rs ->
            Competencia competencia = new Competencia()
            List<TipoCompetencia> competenciasList = new ArrayList<>(Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(rs.getString('competencias')))
            competencia.competencias = competenciasList
            Candidato candidato = new Candidato(rs.getString('nome').trim(), rs.getString('sobrenome'),
                    rs.getDate('data_nascimento'), rs.getString('email'), rs.getString('cpf'),
                    rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'),
                    rs.getString('senha'), competencia)
            listaCandidato.add(candidato)
        }
        return listaCandidato
    }

    int pegaId(String cpf) {
        int idCandidato = 0
        sql.eachRow("""SELECT c.id
                            FROM candidatos c
                            WHERE c.cpf = ${cpf};""") { rs ->
            idCandidato = rs.getInt('id')
        }
        return idCandidato
    }
}
