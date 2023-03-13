package com.igor.linketinder.dao

import com.igor.linketinder.model.Candidato
import com.igor.linketinder.model.TipoCompetencia
import com.igor.linketinder.dao.fabricaBanco.FabricaBanco
import groovy.sql.Sql
import groovy.transform.TypeChecked

@TypeChecked
class CompetenciaCandidatoDAO {

    private Sql sql
    private FabricaBanco fabricaBanco
    private CompetenciaDAO competenciaDAO = new CompetenciaDAO(fabricaBanco)
    private CandidatoDAO candidatoDAO = new CandidatoDAO(fabricaBanco)

    CompetenciaCandidatoDAO(FabricaBanco fabricaBanco) {
        this.fabricaBanco = fabricaBanco
        sql = fabricaBanco.iniciarBancoDeDados().conectar()
    }

    void salvar(Candidato candidato) {
        final int idCandidato = candidatoDAO.pegaId(candidato.cpf)

        for (TipoCompetencia competencia in candidato.competencia.competencias) {
            int idCompetencia = competenciaDAO.pegaId(competencia.toString())
            sql.executeInsert("""INSERT INTO competencias_candidato (candidatos_id, competencia_id)
                                    VALUES (${idCandidato}, ${idCompetencia});""")
        }
    }

    void atualizar(Candidato candidato) {
        final int idCandidato = candidatoDAO.pegaId(candidato.cpf)

        sql.executeInsert("DELETE FROM competencias_candidato WHERE candidatos_id = ${idCandidato}")
        for (TipoCompetencia competencia in candidato.competencia.competencias) {
            int idCompetencia = competenciaDAO.pegaId(competencia.toString())
            sql.executeInsert("""INSERT INTO competencias_candidato (candidatos_id, competencia_id)
                                    VALUES (${idCandidato}, ${idCompetencia});""")
        }
    }

    void remove(String cpf) {
        final int idCandidato = candidatoDAO.pegaId(cpf)

        sql.executeInsert("DELETE FROM competencias_candidato WHERE candidatos_id = ${idCandidato}")
    }
}
