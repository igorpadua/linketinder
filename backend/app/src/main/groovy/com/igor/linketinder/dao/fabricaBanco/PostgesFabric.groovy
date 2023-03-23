package com.igor.linketinder.dao.fabricaBanco


import com.igor.linketinder.dao.database.BancoDados
import com.igor.linketinder.dao.database.PostgreSql
import groovy.transform.TypeChecked

@TypeChecked
class PostgesFabric extends FabricaBanco {
    @Override
    protected BancoDados criarBanco() {
        return PostgreSql.getInstance()
    }
}
