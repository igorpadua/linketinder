package com.igor.linketinder.dao

import groovy.sql.Sql
import groovy.transform.TypeChecked

@TypeChecked
class CompetenciaDAO {
    static final url = 'jdbc:postgresql://localhost/liketinder'
    static final user= 'postgres'
    static final password= '123456'
    static final drive= "org.postgresql.Driver"

    static int pegaId(String competencia) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        List resultado = sql.rows("SELECT id FROM competencias WHERE competencia = ${competencia}")
        sql.close()
        return resultado[0].id as int
    }

}
