package main.groovy.dto

import groovy.sql.Sql
import main.groovy.entity.Vaga

class VagaDTO {
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
        sql.eachRow('SELECT * FROM vagas') { rs ->
            Vaga vaga = new Vaga(rs.getString('nome').trim(), rs.getString('descricao').trim(), rs.getString('local_vaga'))
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
}
