package com.igor.linketinder.dao

import com.igor.linketinder.fabricaBanco.FabricaBanco
import groovy.sql.Sql
import groovy.transform.TypeChecked

@TypeChecked
class CompetenciaDAO {

    private Sql sql

    CompetenciaDAO(FabricaBanco fabricaBanco) {
        this.sql = fabricaBanco.iniciarBancoDeDados().conectar()
    }

    int pegaId(String competencia) {
        List resultado = sql.rows("SELECT id FROM competencias WHERE competencia = ${competencia}")
        return resultado[0].id as int
    }

}
