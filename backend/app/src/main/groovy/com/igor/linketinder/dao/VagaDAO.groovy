package com.igor.linketinder.dao

import com.igor.linketinder.dao.fabricaBanco.FabricaBanco
import com.igor.linketinder.model.Competencia
import groovy.sql.Sql
import com.igor.linketinder.model.TipoCompetencia
import com.igor.linketinder.model.Vaga

class VagaDAO {

    private Sql sql
    private FabricaBanco fabricaBanco
    private CompetenciaVagasDAO competenciaVagasDAO = new CompetenciaVagasDAO(fabricaBanco)

    VagaDAO(FabricaBanco fabricaBanco) {
        this.fabricaBanco = fabricaBanco
        sql = fabricaBanco.iniciarBancoDeDados().conectar()
    }

    static void validaVaga(Vaga vaga) {
        if (vaga == null) {
            throw new RuntimeException("Não foi possível encontrar uma vaga com o ID fornecido.")
        }
    }

    void salvar(Vaga vaga, int idEmpresa) {
        sql.executeInsert('INSERT INTO vagas ' +
                '(nome, descricao, local_vaga, empresa_id) ' +
                "VALUES ('${vaga.nome}', '${vaga.descricao}', '${vaga.local_vaga}', '${idEmpresa}')")
        vaga.id = pegaId(vaga)
        competenciaVagasDAO.salvar(vaga)
    }

    List<Vaga> listaComTodasVagas() {
        List<Vaga> listaVagas = []
        sql.eachRow("""select * from vagas as v""") { rs ->
            Integer id = rs.getInt('id')
            List<Competencia> competencias = competenciaVagasDAO.pegaCompetencias(id)
            Vaga vaga = new Vaga(id, rs.getString('nome').trim(), rs.getString('descricao').trim(),
                    rs.getString('local_vaga'), competencias)
            listaVagas.add(vaga)
            }
        return listaVagas
    }

    void remove(int id) {
        sql.execute("DELETE FROM vagas WHERE id = ${id}")
    }

    void atualiza(Vaga vaga) {
        println(vaga)
        sql.executeUpdate('UPDATE vagas ' +
                "SET nome = '${vaga.nome}', descricao = '${vaga.descricao}', local_vaga = '${vaga.local_vaga}' " +
                "WHERE id = '${vaga.id}'")
        competenciaVagasDAO.atualizar(vaga)
    }

    Vaga pega(int id) {
        Vaga vaga = null
        sql.eachRow("""select v.id, v.nome, v.descricao, v.local_vaga, array_agg(c.competencia) as competencias
	                   from vagas as v
	                   INNER JOIN competencia_vagas cv ON cv.vagas_id = v.id
	                   INNER JOIN competencias c ON c.id = cv.competencia_id
	                   WHERE v.id = ${id} 
	                   GROUP BY v.id, v.nome, v.descricao, v.local_vaga;""") { rs ->
            List<Competencia> competencia = competenciaVagasDAO.pegaCompetencias(id)
            vaga = new Vaga(rs.getInt('id'), rs.getString('nome').trim(), rs.getString('descricao').trim(), rs.getString('local_vaga'), competencia)
        }
        validaVaga(vaga)
        return vaga
    }

    int pegaId(Vaga vaga) {
        int id = 0
        sql.eachRow("SELECT id FROM vagas WHERE nome = ${vaga.nome}" +
                "and descricao = ${vaga.descricao}" +
                "and local_vaga = ${vaga.local_vaga}") { rs ->
            id = rs.getInt('id')
        }
        return id
    }
}
