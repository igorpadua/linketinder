package com.igor.linketinder.dao.database

import groovy.sql.Sql
import groovy.transform.TypeChecked

@TypeChecked
class PostgreSql implements BancoDados {
    final url = 'jdbc:postgresql://localhost/liketinder'
    final user= 'postgres'
    final password= '123456'
    final drive= "org.postgresql.Driver"

    Sql conectar() {
        Sql sql = Sql.newInstance(url, user, password, drive)
        return sql
    }
}
