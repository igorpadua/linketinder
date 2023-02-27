package main.groovy.dto

import groovy.sql.Sql
import groovy.transform.TypeChecked

@TypeChecked
class CompetenciaDTO {
    static final url = 'jdbc:postgresql://localhost/liketinder'
    static final user= 'postgres'
    static final password= '123456'
    static final drive= "org.postgresql.Driver"

    static List getCompetencias() {
        Sql sql = Sql.newInstance(url, user, password, drive)
        List result = sql.rows('SELECT * FROM competencias')
        sql.close()
        return result
    }

    static int getIdCompetencia(String competencia) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        List result = sql.rows("SELECT id FROM competencias WHERE competencia = ${competencias}")
        sql.close()
        return result[0].id as int
    }
}
