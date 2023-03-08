package com.igor.linketinder.dao

import groovy.sql.Sql
import com.igor.linketinder.entity.Competencia
import com.igor.linketinder.entity.Vaga

class CompetenciaVagasDAO {
    static final url = 'jdbc:postgresql://localhost/liketinder'
    static final user= 'postgres'
    static final password= '123456'
    static final drive= "org.postgresql.Driver"

    static void adicionar(Vaga vaga, int id) {
        Sql sql = Sql.newInstance(url, user, password, drive)

        for (Competencia competencia in vaga.competencias) {
            int idCompetencia = CompetenciaDAO.pegaId(competencia.toString())
            sql.executeInsert("""INSERT INTO competencia_vagas (vagas_id, competencia_id)
                                    VALUES (${id}, ${idCompetencia});""")
        }

        sql.close()
    }

    static void remove(int id) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeInsert("DELETE FROM competencia_vagas WHERE vagas_id = ${id}")
        sql.close()
    }

    static void atualizar(Vaga vaga, int id) {
        Sql sql = Sql.newInstance(url, user, password, drive)

        remove(id)
        for (Competencia competencia in vaga.competencias) {
            int idCompetencia = CompetenciaDAO.pegaId(competencia.toString())
            sql.executeInsert("""INSERT INTO competencia_vagas (vagas_id, competencia_id)
                                    VALUES (${id}, ${idCompetencia});""")
        }

        sql.close()
    }
}
