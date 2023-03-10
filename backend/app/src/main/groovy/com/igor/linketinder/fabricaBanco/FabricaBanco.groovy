package com.igor.linketinder.fabricaBanco

import com.igor.linketinder.database.BancoDados

abstract class FabricaBanco {

    BancoDados iniciarBancoDeDados() {
        BancoDados bancoDados = criarBanco()
        return bancoDados
    }

    abstract BancoDados criarBanco()
}
