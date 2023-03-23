package com.igor.linketinder.dao.fabricaBanco

import com.igor.linketinder.dao.database.BancoDados
import groovy.transform.TypeChecked

@TypeChecked
abstract class FabricaBanco {

    BancoDados iniciarBancoDeDados() {
        BancoDados bancoDados = criarBanco()
        return bancoDados
    }

    protected abstract BancoDados criarBanco()
}
