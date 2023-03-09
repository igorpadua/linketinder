package com.igor.linketinder.dao

import groovy.sql.Sql
import com.igor.linketinder.entity.Competencia
import com.igor.linketinder.entity.Vaga

class CompetenciaVagasDAO extends ConectarBanco {

    static void adicionar(Vaga vaga, int id) {
        Sql sql = conectar()

        for (Competencia competencia in vaga.competencias) {
            int idCompetencia = CompetenciaDAO.pegaId(competencia.toString())
            sql.executeInsert("""INSERT INTO competencia_vagas (vagas_id, competencia_id)
                                    VALUES (${id}, ${idCompetencia});""")
        }

        desconectar(sql)
    }

    static void remove(int id) {
        Sql sql = conectar()
        sql.executeInsert("DELETE FROM competencia_vagas WHERE vagas_id = ${id}")
        desconectar(sql)
    }

    static void atualizar(Vaga vaga, int id) {
        Sql sql = conectar()

        remove(id)
        for (Competencia competencia in vaga.competencias) {
            int idCompetencia = CompetenciaDAO.pegaId(competencia.toString())
            sql.executeInsert("""INSERT INTO competencia_vagas (vagas_id, competencia_id)
                                    VALUES (${id}, ${idCompetencia});""")
        }

        desconectar(sql)
    }
}
