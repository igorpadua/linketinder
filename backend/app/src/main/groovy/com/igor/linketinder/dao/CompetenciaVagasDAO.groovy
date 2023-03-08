package com.igor.linketinder.dao

import groovy.sql.Sql
import com.igor.linketinder.entity.Competencia
import com.igor.linketinder.entity.Vaga

class CompetenciaVagasDAO {
    static final url = 'jdbc:postgresql://localhost/liketinder'
    static final user= 'postgres'
    static final password= '123456'
    static final drive= "org.postgresql.Driver"

    static void inserirCompetenciaVaga(Vaga vaga, int id) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        final int idVaga = VagaDAO.getIdVaga(vaga.nome, id)

        for (Competencia competencia in vaga.competencias) {
            int idCompetencia = CompetenciaDAO.pegaId(competencia.toString())
            sql.executeInsert("""INSERT INTO competencia_vagas (vagas_id, competencia_id)
                                    VALUES (${idVaga}, ${idCompetencia});""")
        }

        sql.close()
    }

    static void removeCompetenciaVaga(int id) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeInsert("DELETE FROM competencia_vagas WHERE vagas_id = ${id}")
        sql.close()
    }

    static void atualizarCompetenciaVaga(Vaga vaga, int id) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        final int idVaga = VagaDAO.getIdVaga(vaga.nome, id)

        sql.executeInsert("DELETE FROM competencia_vagas WHERE vagas_id = ${idVaga}")
        for (Competencia competencia in vaga.competencias) {
            int idCompetencia = CompetenciaDAO.pegaId(competencia.toString())
            sql.executeInsert("""INSERT INTO competencia_vagas (vagas_id, competencia_id)
                                    VALUES (${idVaga}, ${idCompetencia});""")
        }

        sql.close()
    }
}
