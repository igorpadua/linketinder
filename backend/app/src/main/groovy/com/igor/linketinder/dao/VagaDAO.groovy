package com.igor.linketinder.dao


import com.igor.linketinder.service.CompetenciaService
import groovy.sql.Sql
import com.igor.linketinder.entity.Competencia
import com.igor.linketinder.entity.Vaga

class VagaDAO {
    static final url = 'jdbc:postgresql://localhost/liketinder'
    static final user= 'postgres'
    static final password= '123456'
    static final drive= "org.postgresql.Driver"

    static void inserirVaga(Vaga vaga, int id) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeInsert('INSERT INTO vagas ' +
                '(nome, descricao, local_vaga, empresa_id) ' +
                "VALUES ('${vaga.nome}', '${vaga.descricao}', '${vaga.local_vaga}', '${id}')")
        sql.close()
    }

    static List<Vaga> listarVagas() {
        Sql sql = Sql.newInstance(url, user, password, drive)
        List<Vaga> result = []
        sql.eachRow("""select v.nome, v.descricao, v.local_vaga, array_agg(c.competencia) as competencias
	                        from vagas as v
                        	INNER JOIN competencia_vagas cv ON cv.vagas_id = v.id
                        	INNER JOIN competencias c ON c.id = cv.competencia_id
                        	GROUP BY v.nome, v.descricao, v.local_vaga;""") { rs ->
            List<Competencia> competenciasList = new ArrayList<>(CompetenciaService.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(rs.getString('competencias')))
            Vaga vaga = new Vaga(rs.getString('nome').trim(), rs.getString('descricao').trim(), rs.getString('local_vaga'), competenciasList)
            result.add(vaga)
            }
        sql.close()
        return result
    }

    static void removeVaga(String nome, int id) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.execute("DELETE FROM vagas WHERE nome = ${nome} and empresa_id = ${id}")
        sql.close()
    }

    static void atualizarVaga(Vaga vaga, nome, int id) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeUpdate('UPDATE vagas ' +
                "SET nome = '${vaga.nome}', descricao = '${vaga.descricao}', local_vaga = '${vaga.local_vaga}' " +
                "WHERE nome = '${nome}' and empresa_id = ${id}")
        sql.close()
    }

    static Vaga getVaga(String nome, int id) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        Vaga vaga = null
        sql.eachRow("""select v.nome, v.descricao, v.local_vaga, array_agg(c.competencia) as competencias
	                   from vagas as v
	                   INNER JOIN competencia_vagas cv ON cv.vagas_id = v.id
	                   INNER JOIN competencias c ON c.id = cv.competencia_id
	                   WHERE nome = ${nome} and empresa_id = ${id} 
	                   GROUP BY v.nome, v.descricao, v.local_vaga;""") { rs ->
            List<Competencia> competenciasList = new ArrayList<>(CompetenciaService.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(rs.getString('competencias')))
            vaga = new Vaga(rs.getString('nome').trim(), rs.getString('descricao').trim(), rs.getString('local_vaga'), competenciasList)
        }
        sql.close()
        return vaga
    }

    static int getIdVaga(String nome, int id) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        int idVaga = 0
        sql.eachRow("SELECT id FROM vagas WHERE nome = ${nome} and empresa_id = ${id}") { rs ->
            idVaga = rs.getInt('id')
        }
        sql.close()
        return idVaga
    }
}
