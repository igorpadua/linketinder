package com.igor.linketinder.dao

import com.igor.linketinder.dao.fabricaBanco.FabricaBanco
import groovy.sql.Sql
import com.igor.linketinder.model.TipoCompetencia
import com.igor.linketinder.model.Vaga

class CompetenciaVagasDAO {

    private Sql sql
    private FabricaBanco fabricaBanco
    private CompetenciaDAO competenciaDAO = new CompetenciaDAO(fabricaBanco)


    CompetenciaVagasDAO(FabricaBanco fabricaBanco) {
        this.fabricaBanco = fabricaBanco
        sql = fabricaBanco.iniciarBancoDeDados().conectar()
    }

    void salvar(Vaga vaga) {
        for (TipoCompetencia competencia in vaga.competencia.competencias) {
            int idCompetencia = competenciaDAO.pegaId(competencia.toString())
            sql.executeInsert("""INSERT INTO competencia_vagas (vagas_id, competencia_id)
                                    VALUES (${vaga.id}, ${idCompetencia});""")
        }
    }

    void remove(int id) {
        sql.executeInsert("DELETE FROM competencia_vagas WHERE vagas_id = ${id}")
    }

    void atualizar(Vaga vaga) {
        remove(vaga.id)
        for (TipoCompetencia competencia in vaga.competencia.competencias) {
            int idCompetencia = competenciaDAO.pegaId(competencia.toString())
            sql.executeInsert("""INSERT INTO competencia_vagas (vagas_id, competencia_id)
                                    VALUES (${vaga.id}, ${idCompetencia});""")
        }
    }
}
