package com.igor.linketinder.fabricaBanco

import com.igor.linketinder.database.BancoDados
import com.igor.linketinder.database.PostgreSql

class PostgesFabric extends FabricaBanco {
    @Override
    BancoDados criarBanco() {
        return new PostgreSql()
    }
}
