package com.igor.linketinder.dao

import groovy.sql.Sql
import groovy.transform.TypeChecked

@TypeChecked
class CompetenciaDAO extends ConectarBanco {

    static int pegaId(String competencia) {
        Sql sql = conectar()
        List resultado = sql.rows("SELECT id FROM competencias WHERE competencia = ${competencia}")
        desconectar(sql)
        return resultado[0].id as int
    }

}
