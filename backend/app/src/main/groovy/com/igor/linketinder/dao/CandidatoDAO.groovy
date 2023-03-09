package com.igor.linketinder.dao

import com.igor.linketinder.entity.Candidato
import com.igor.linketinder.entity.Competencia
import com.igor.linketinder.service.CompetenciaService
import groovy.sql.Sql
import groovy.transform.TypeChecked

import java.text.SimpleDateFormat

@TypeChecked
class CandidatoDAO extends ConectarBanco {
    private static validaCandidato(Candidato candidato) {
        if (candidato == null) {
            throw new RuntimeException("Não foi possível encontrar um candidato com o CPF fornecido.")
        }
    }

    static void adiciona(Candidato candidato) {
        Sql sql = conectar()
        String nascimento = new SimpleDateFormat("yyyy-MM-dd").format(candidato.nascimento)

        sql.executeInsert('INSERT INTO candidatos ' +
                '(nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha) ' +
                "VALUES ('${candidato.nome}', '${candidato.sobrenome}', '${nascimento}', " +
                "'${candidato.email}', '${candidato.cpf}', '${candidato.pais}', '${candidato.cep}', " +
                "'${candidato.descricao}', '${candidato.senha}')")

        desconectar(sql)
    }

    static void atualiza(Candidato candidato) {
        Sql sql = conectar()
        String nascimento = new SimpleDateFormat("yyyy-MM-dd").format(candidato.nascimento)

        sql.executeInsert('UPDATE candidatos ' +
                "SET nome = '${candidato.nome}', sobrenome = '${candidato.sobrenome}', " +
                "data_nascimento = '${nascimento}', email = '${candidato.email}', " +
                "cpf = '${candidato.cpf}', pais = '${candidato.pais}', cep = '${candidato.cep}', " +
                "descricao = '${candidato.descricao}', senha = '${candidato.senha}' " +
                "WHERE cpf = '${candidato.cpf}'")

        desconectar(sql)
    }

    static void remove(String cpf) {
        Sql sql = conectar()

        validaCandidato(pega(cpf))

        sql.executeInsert('DELETE FROM candidatos ' +
                "WHERE cpf = '${cpf}'")

        desconectar(sql)
    }


    static Candidato pega(String cpf) {
        Sql sql = conectar()

        Candidato candidato = null
        sql.eachRow("""SELECT c.nome, c.sobrenome, c.data_nascimento, c.email, c.cpf,
                            c.pais, c.cep, c.descricao, c.senha, array_agg(competencia.competencia) as competencias
                            FROM candidatos c
                            INNER JOIN competencias_candidato cc ON cc.candidatos_id = c.id
                            INNER JOIN competencias competencia ON competencia.id = cc.competencia_id
                            WHERE c.cpf = ${cpf}
                            GROUP BY c.id;""") { rs ->
            List<Competencia> competenciasList = new ArrayList<>(CompetenciaService.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(rs.getString('competencias')))
            Date data = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString('data_nascimento'))

            candidato = new Candidato(rs.getString('nome'), rs.getString('sobrenome'), data, rs.getString('email'),
                    rs.getString('cpf'), rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'),
                    rs.getString('senha'), competenciasList)
        }

        desconectar(sql)
        validaCandidato(candidato)
        return candidato
    }

    static List<Candidato> listaComTodosCandidatos() {
        Sql sql = conectar()
        List<Candidato> listaCandidato = new ArrayList<>()

        sql.eachRow("""SELECT c.nome, c.sobrenome, c.data_nascimento, c.email, c.cpf,
                            c.pais, c.cep, c.descricao, c.senha, array_agg(competencia.competencia) as competencias
                            FROM candidatos c
                            INNER JOIN competencias_candidato cc ON cc.candidatos_id = c.id
                            INNER JOIN competencias competencia ON competencia.id = cc.competencia_id
                            GROUP BY c.id;""") { rs ->
            List<Competencia> competenciasList = new ArrayList<>(CompetenciaService.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(rs.getString('competencias')))
            Candidato candidato = new Candidato(rs.getString('nome').trim(), rs.getString('sobrenome'),
                    rs.getDate('data_nascimento'), rs.getString('email'), rs.getString('cpf'),
                    rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'),
                    rs.getString('senha'), competenciasList)
            listaCandidato.add(candidato)
        }

        desconectar(sql)

        return listaCandidato
    }

    static int pegaId(String cpf) {
        Sql sql = conectar()
        int idCandidato = 0
        sql.eachRow("""SELECT c.id
                            FROM candidatos c
                            WHERE c.cpf = ${cpf};""") { rs ->
            idCandidato = rs.getInt('id')
        }
        desconectar(sql)
        return idCandidato
    }
}
