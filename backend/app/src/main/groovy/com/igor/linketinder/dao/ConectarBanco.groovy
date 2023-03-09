package com.igor.linketinder.dao

import groovy.sql.Sql

abstract class ConectarBanco {
    static final url = 'jdbc:postgresql://localhost/liketinder'
    static final user= 'postgres'
    static final password= '123456'
    static final drive= "org.postgresql.Driver"

    static conectar() {
        Sql sql = Sql.newInstance(url, user, password, drive)
        return sql
    }

    static desconectar(Sql sql) {
        sql.close()
    }
}
