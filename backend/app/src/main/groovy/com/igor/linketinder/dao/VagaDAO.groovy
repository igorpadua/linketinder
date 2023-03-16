package com.igor.linketinder.dao

import com.igor.linketinder.dao.fabricaBanco.FabricaBanco
import com.igor.linketinder.model.Competencia
import groovy.sql.Sql
import com.igor.linketinder.model.TipoCompetencia
import com.igor.linketinder.model.Vaga

class VagaDAO {

    private Sql sql

    VagaDAO(FabricaBanco fabricaBanco) {
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
    }

    List<Vaga> listaComTodasVagas() {
        List<Vaga> listaVagas = []
        sql.eachRow("""select v.id, v.nome, v.descricao, v.local_vaga, array_agg(c.competencia) as competencias
	                        from vagas as v
                        	INNER JOIN competencia_vagas cv ON cv.vagas_id = v.id
                        	INNER JOIN competencias c ON c.id = cv.competencia_id
                        	GROUP BY v.id, v.nome, v.descricao, v.local_vaga;""") { rs ->
            Competencia competencia = new Competencia()
            List<TipoCompetencia> competenciasList = new ArrayList<>(Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(rs.getString('competencias')))
            competencia.competencias = competenciasList
            Vaga vaga = new Vaga(rs.getInt('id'),rs.getString('nome').trim(), rs.getString('descricao').trim(), rs.getString('local_vaga'), competencia)
            listaVagas.add(vaga)
            }
        return listaVagas
    }

    void remove(int id) {
        sql.execute("DELETE FROM vagas WHERE id = ${id}")
    }

    void atualiza(Vaga vaga) {
        sql.executeUpdate('UPDATE vagas ' +
                "SET nome = '${vaga.nome}', descricao = '${vaga.descricao}', local_vaga = '${vaga.local_vaga}' " +
                "WHERE id = '${vaga.id}'")
    }

    Vaga pega(int id) {
        Vaga vaga = null
        sql.eachRow("""select v.id, v.nome, v.descricao, v.local_vaga, array_agg(c.competencia) as competencias
	                   from vagas as v
	                   INNER JOIN competencia_vagas cv ON cv.vagas_id = v.id
	                   INNER JOIN competencias c ON c.id = cv.competencia_id
	                   WHERE v.id = ${id} 
	                   GROUP BY v.id, v.nome, v.descricao, v.local_vaga;""") { rs ->
            Competencia competencia = new Competencia()
            List<TipoCompetencia> competenciasList = new ArrayList<>(Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(rs.getString('competencias')))
            competencia.competencias = competenciasList
            vaga = new Vaga(rs.getInt('id'), rs.getString('nome').trim(), rs.getString('descricao').trim(), rs.getString('local_vaga'), competencia)
        }
        validaVaga(vaga)
        return vaga
    }
}
