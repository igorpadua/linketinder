package com.igor.linketinder.dao

import com.igor.linketinder.entity.Candidato
import com.igor.linketinder.entity.Competencia
import groovy.sql.Sql
import groovy.transform.TypeChecked

@TypeChecked
class CompetenciaCandidatoDAO extends ConectarBanco {

    static void adicionar(Candidato candidato) {
        Sql sql = conectar()
        final int idCandidato = CandidatoDAO.pegaId(candidato.cpf)

        for (Competencia competencia in candidato.competencias) {
            int idCompetencia = CompetenciaDAO.pegaId(competencia.toString())
            sql.executeInsert("""INSERT INTO competencias_candidato (candidatos_id, competencia_id)
                                    VALUES (${idCandidato}, ${idCompetencia});""")
        }

        desconectar(sql)
    }

    static void atualizar(Candidato candidato) {
        Sql sql = conectar()
        final int idCandidato = CandidatoDAO.pegaId(candidato.cpf)

        sql.executeInsert("DELETE FROM competencias_candidato WHERE candidatos_id = ${idCandidato}")
        for (Competencia competencia in candidato.competencias) {
            int idCompetencia = CompetenciaDAO.pegaId(competencia.toString())
            sql.executeInsert("""INSERT INTO competencias_candidato (candidatos_id, competencia_id)
                                    VALUES (${idCandidato}, ${idCompetencia});""")
        }

        desconectar(sql)
    }

    static void remove(String cpf) {
        Sql sql = conectar()
        final int idCandidato = CandidatoDAO.pegaId(cpf)

        sql.executeInsert("DELETE FROM competencias_candidato WHERE candidatos_id = ${idCandidato}")

        desconectar(sql)
    }
}
