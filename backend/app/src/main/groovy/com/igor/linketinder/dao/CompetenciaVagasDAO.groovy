package com.igor.linketinder.dao

import com.igor.linketinder.fabricaBanco.FabricaBanco
import groovy.sql.Sql
import com.igor.linketinder.model.Competencia
import com.igor.linketinder.model.Vaga

class CompetenciaVagasDAO {

    private Sql sql
    private FabricaBanco fabricaBanco
    private CompetenciaDAO competenciaDAO = new CompetenciaDAO(fabricaBanco)


    CompetenciaVagasDAO(FabricaBanco fabricaBanco) {
        this.fabricaBanco = fabricaBanco
        sql = fabricaBanco.iniciarBancoDeDados().conectar()
    }

    void adicionar(Vaga vaga, int id) {
        for (Competencia competencia in vaga.competencias) {
            int idCompetencia = competenciaDAO.pegaId(competencia.toString())
            sql.executeInsert("""INSERT INTO competencia_vagas (vagas_id, competencia_id)
                                    VALUES (${id}, ${idCompetencia});""")
        }
    }

    void remove(int id) {
        sql.executeInsert("DELETE FROM competencia_vagas WHERE vagas_id = ${id}")
    }

    void atualizar(Vaga vaga, int id) {
        remove(id)
        for (Competencia competencia in vaga.competencias) {
            int idCompetencia = competenciaDAO.pegaId(competencia.toString())
            sql.executeInsert("""INSERT INTO competencia_vagas (vagas_id, competencia_id)
                                    VALUES (${id}, ${idCompetencia});""")
        }
    }
}
