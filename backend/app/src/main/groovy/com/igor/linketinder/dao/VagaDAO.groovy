package com.igor.linketinder.dao

import com.igor.linketinder.service.CompetenciaService
import com.igor.linketinder.view.CompetenciaView
import groovy.sql.Sql
import com.igor.linketinder.entity.Competencia
import com.igor.linketinder.entity.Vaga

class VagaDAO {
    static final url = 'jdbc:postgresql://localhost/liketinder'
    static final user= 'postgres'
    static final password= '123456'
    static final drive= "org.postgresql.Driver"

    static validaVaga(Vaga vaga) {
        if (vaga == null) {
            throw new RuntimeException("Não foi possível encontrar uma vaga com o ID fornecido.")
        }
    }

    static void adicionar(Vaga vaga, int idEmpresa) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeInsert('INSERT INTO vagas ' +
                '(nome, descricao, local_vaga, empresa_id) ' +
                "VALUES ('${vaga.nome}', '${vaga.descricao}', '${vaga.local_vaga}', '${idEmpresa}')")
        sql.close()
    }

    static List<Vaga> listaComTodasVagas() {
        Sql sql = Sql.newInstance(url, user, password, drive)
        List<Vaga> listaVagas = []
        sql.eachRow("""select v.id, v.nome, v.descricao, v.local_vaga, array_agg(c.competencia) as competencias
	                        from vagas as v
                        	INNER JOIN competencia_vagas cv ON cv.vagas_id = v.id
                        	INNER JOIN competencias c ON c.id = cv.competencia_id
                        	GROUP BY v.id, v.nome, v.descricao, v.local_vaga;""") { rs ->
            List<Competencia> competenciasList = new ArrayList<>(CompetenciaService.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(rs.getString('competencias')))
            Vaga vaga = new Vaga(rs.getInt('id'),rs.getString('nome').trim(), rs.getString('descricao').trim(), rs.getString('local_vaga'), competenciasList)
            listaVagas.add(vaga)
            }
        sql.close()
        return listaVagas
    }

    static void remove(int id) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.execute("DELETE FROM vagas WHERE id = ${id}")
        sql.close()
    }

    static void atualiza(Vaga vaga, int id) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeUpdate('UPDATE vagas ' +
                "SET nome = '${vaga.nome}', descricao = '${vaga.descricao}', local_vaga = '${vaga.local_vaga}' " +
                "WHERE id = '${id}'")
        sql.close()
    }

    static Vaga pega(int id) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        Vaga vaga = null
        sql.eachRow("""select v.nome, v.descricao, v.local_vaga, array_agg(c.competencia) as competencias
	                   from vagas as v
	                   INNER JOIN competencia_vagas cv ON cv.vagas_id = v.id
	                   INNER JOIN competencias c ON c.id = cv.competencia_id
	                   WHERE v.id = ${id} 
	                   GROUP BY v.nome, v.descricao, v.local_vaga;""") { rs ->
            List<Competencia> competenciasList = new ArrayList<>(CompetenciaService.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(rs.getString('competencias')))
            vaga = new Vaga(rs.getInt('id'), rs.getString('nome').trim(), rs.getString('descricao').trim(), rs.getString('local_vaga'), competenciasList)
        }
        sql.close()
        validaVaga(vaga)
        return vaga
    }
}
