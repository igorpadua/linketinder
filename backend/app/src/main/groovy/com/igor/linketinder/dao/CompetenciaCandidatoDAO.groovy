package com.igor.linketinder.dao

import com.igor.linketinder.model.Candidato
import com.igor.linketinder.model.Competencia
import com.igor.linketinder.model.TipoCompetencia
import com.igor.linketinder.dao.fabricaBanco.FabricaBanco
import groovy.sql.Sql
import groovy.transform.TypeChecked

@TypeChecked
class CompetenciaCandidatoDAO {

    private Sql sql
    private FabricaBanco fabricaBanco
    private CompetenciaDAO competenciaDAO = new CompetenciaDAO(fabricaBanco)


    CompetenciaCandidatoDAO(FabricaBanco fabricaBanco) {
        this.fabricaBanco = fabricaBanco
        sql = fabricaBanco.iniciarBancoDeDados().conectar()
    }

    List<Competencia> pegar(Integer id) {
        List<Competencia> competencias = []
        sql.eachRow("""select c.competencia 
                            FROM competencias c 
                            INNER JOIN competencias_candidato cc on c.id = cc.competencia_id 
                            WHERE candidatos_id = ${id};""") { rs ->
            TipoCompetencia tipoCompetencia = TipoCompetencia.valueOf(rs.getString("competencia"))
            Competencia competencia = new Competencia(tipoCompetencia)
            competencias.add(competencia)
        }
        return competencias
    }

    void salvar(Candidato candidato) {
        for (TipoCompetencia competencia in candidato.competencias.competencia) {
            int idCompetencia = competenciaDAO.pegaId(competencia.toString())
            sql.executeInsert("""INSERT INTO competencias_candidato (candidatos_id, competencia_id)
                                    VALUES (${candidato.id}, ${idCompetencia});""")
        }
    }

    void atualizar(Candidato candidato) {
        remove(candidato.id)
        for (TipoCompetencia competencia in candidato.competencias.competencia) {
            int idCompetencia = competenciaDAO.pegaId(competencia.toString())
            sql.executeInsert("""INSERT INTO competencias_candidato (candidatos_id, competencia_id)
                                    VALUES (${candidato.id}, ${idCompetencia});""")
        }
    }

    void remove(Integer idCandidato) {
        sql.executeInsert("DELETE FROM competencias_candidato WHERE candidatos_id = ${idCandidato}")
    }
}
