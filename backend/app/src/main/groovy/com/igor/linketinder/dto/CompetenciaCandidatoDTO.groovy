package com.igor.linketinder.dto

import com.igor.linketinder.entity.Candidato
import com.igor.linketinder.entity.Competencia
import groovy.sql.Sql
import groovy.transform.TypeChecked
import com.igor.linketinder.dto.CompetenciaDTO

@TypeChecked
class CompetenciaCandidatoDTO {
    static final url = 'jdbc:postgresql://localhost/liketinder'
    static final user= 'postgres'
    static final password= '123456'
    static final drive= "org.postgresql.Driver"

    static void inserirCompetenciaCandidato(Candidato candidato) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        final int idCandidato = CandidatoDTO.getIdCandidato(candidato.cpf)
        for (Competencia competencia in candidato.competencias) {
            int idCompetencia = CompetenciaDTO.getIdCompetencia(competencia.toString())
            sql.executeInsert("""INSERT INTO competencias_candidato (candidatos_id, competencia_id)
                                    VALUES (${idCandidato}, ${idCompetencia});""")
        }
        sql.close()
    }

    static void atualizarCompetenciaCandidato(Candidato candidato) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        final int idCandidato = CandidatoDTO.getIdCandidato(candidato.cpf)
        sql.executeInsert("DELETE FROM competencias_candidato WHERE candidatos_id = ${idCandidato}")
        for (Competencia competencia in candidato.competencias) {
            int idCompetencia = CompetenciaDTO.getIdCompetencia(competencia.toString())
            sql.executeInsert("""INSERT INTO competencias_candidato (candidatos_id, competencia_id)
                                    VALUES (${idCandidato}, ${idCompetencia});""")
        }
        sql.close()
    }

    static void removeCompetenciaCandidato(String cpf) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        final int idCandidato = CandidatoDTO.getIdCandidato(cpf)
        sql.executeInsert("DELETE FROM competencias_candidato WHERE candidatos_id = ${idCandidato}")
        sql.close()
    }
}
