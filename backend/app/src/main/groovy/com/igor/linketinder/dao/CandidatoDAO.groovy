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
    private FabricaBanco fabricaBanco
    private CompetenciaCandidatoDAO competenciaCandidatoDAO = new CompetenciaCandidatoDAO(fabricaBanco)

    CandidatoDAO(FabricaBanco fabricaBanco) {
        this.fabricaBanco = fabricaBanco
        sql = fabricaBanco.iniciarBancoDeDados().conectar()
    }

    void salvar(Candidato candidato) {
        String nascimento = new SimpleDateFormat("yyyy-MM-dd").format(candidato.nascimento)

        sql.executeInsert('INSERT INTO candidatos ' +
                '(nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha) ' +
                "VALUES ('${candidato.nome}', '${candidato.sobrenome}', '${nascimento}', " +
                "'${candidato.email}', '${candidato.cpf}', '${candidato.pais}', '${candidato.cep}', " +
                "'${candidato.descricao}', '${candidato.senha}')")
        candidato.id = pegaId(candidato.cpf)
        competenciaCandidatoDAO.salvar(candidato)
    }

    void atualiza(Candidato candidato) {
        String nascimento = new SimpleDateFormat("yyyy-MM-dd").format(candidato.nascimento)

        sql.executeInsert('UPDATE candidatos ' +
                "SET nome = '${candidato.nome}', sobrenome = '${candidato.sobrenome}', " +
                "data_nascimento = '${nascimento}', email = '${candidato.email}', " +
                "cpf = '${candidato.cpf}', pais = '${candidato.pais}', cep = '${candidato.cep}', " +
                "descricao = '${candidato.descricao}', senha = '${candidato.senha}' " +
                "WHERE id = '${candidato.id}'")
        competenciaCandidatoDAO.atualizar(candidato)
    }

    void remove(Integer id) {
        sql.executeInsert("DELETE FROM candidatos WHERE id = ${id}")
    }


    Candidato pegar(Integer idCandidato) {

        Candidato candidato = null
        sql.eachRow("""SELECT * FROM candidatos c WHERE c.id = ${idCandidato} ;""") { rs ->
            Date nascimento = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString('data_nascimento'))
            List<Competencia> competencias = competenciaCandidatoDAO.pegar(idCandidato)

            candidato = new Candidato(idCandidato, rs.getString('nome'), rs.getString('sobrenome'), nascimento, rs.getString('email'),
                    rs.getString('cpf'), rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'),
                    rs.getString('senha'), competencias)
        }
        return candidato
    }

    List<Candidato> listaComTodosCandidatos() {
        List<Candidato> listaCandidato = new ArrayList<>()

        sql.eachRow("""SELECT c.id, c.nome, c.sobrenome, c.data_nascimento, c.email, c.cpf,
                            c.pais, c.cep, c.descricao, c.senha, array_agg(competencia.competencia) as competencias
                            FROM candidatos c
                            INNER JOIN competencias_candidato cc ON cc.candidatos_id = c.id
                            INNER JOIN competencias competencia ON competencia.id = cc.competencia_id
                            GROUP BY c.id;""") { rs ->
            Integer id = rs.getInt('id')
            List<Competencia> competencias = competenciaCandidatoDAO.pegar(id)

            Candidato candidato = new Candidato(id, rs.getString('nome').trim(), rs.getString('sobrenome'),
                    rs.getDate('data_nascimento'), rs.getString('email'), rs.getString('cpf'),
                    rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'),
                    rs.getString('senha'), competencias)
            listaCandidato.add(candidato)
        }
        return listaCandidato
    }

    Integer pegaId(String cpf) {
        Integer id = null
        sql.eachRow("SELECT id FROM candidatos WHERE cpf = ${cpf}") { rs ->
            id = rs.getInt('id')
        }
        return id
    }
}
