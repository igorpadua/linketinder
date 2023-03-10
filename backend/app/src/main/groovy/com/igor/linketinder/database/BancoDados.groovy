package com.igor.linketinder.database

import groovy.sql.Sql
import groovy.transform.TypeChecked

@TypeChecked
interface BancoDados {
    Sql conectar()
}
