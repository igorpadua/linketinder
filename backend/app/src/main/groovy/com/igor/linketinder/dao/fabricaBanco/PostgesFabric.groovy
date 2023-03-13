package com.igor.linketinder.dao.fabricaBanco


import com.igor.linketinder.dao.database.BancoDados
import com.igor.linketinder.dao.database.PostgreSql

class PostgesFabric extends FabricaBanco {
    @Override
    protected BancoDados criarBanco() {
        return new PostgreSql()
    }
}
