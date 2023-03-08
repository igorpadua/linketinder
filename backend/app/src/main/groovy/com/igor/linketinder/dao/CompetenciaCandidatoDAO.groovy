package com.igor.linketinder.dao

import com.igor.linketinder.entity.Candidato
import com.igor.linketinder.entity.Competencia
import groovy.sql.Sql
import groovy.transform.TypeChecked

@TypeChecked
class CompetenciaCandidatoDAO {
    static final url = 'jdbc:postgresql://localhost/liketinder'
    static final user= 'postgres'
    static final password= '123456'
    static final drive= "org.postgresql.Driver"

    static void adicionar(Candidato candidato) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        final int idCandidato = CandidatoDAO.pegaId(candidato.cpf)

        for (Competencia competencia in candidato.competencias) {
            int idCompetencia = CompetenciaDAO.getIdCompetencia(competencia.toString())
            sql.executeInsert("""INSERT INTO competencias_candidato (candidatos_id, competencia_id)
                                    VALUES (${idCandidato}, ${idCompetencia});""")
        }

        sql.close()
    }

    static void atualizar(Candidato candidato) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        final int idCandidato = CandidatoDAO.pegaId(candidato.cpf)

        sql.executeInsert("DELETE FROM competencias_candidato WHERE candidatos_id = ${idCandidato}")
        for (Competencia competencia in candidato.competencias) {
            int idCompetencia = CompetenciaDAO.getIdCompetencia(competencia.toString())
            sql.executeInsert("""INSERT INTO competencias_candidato (candidatos_id, competencia_id)
                                    VALUES (${idCandidato}, ${idCompetencia});""")
        }

        sql.close()
    }

    static void remove(String cpf) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        final int idCandidato = CandidatoDAO.pegaId(cpf)

        sql.executeInsert("DELETE FROM competencias_candidato WHERE candidatos_id = ${idCandidato}")

        sql.close()
    }
}
