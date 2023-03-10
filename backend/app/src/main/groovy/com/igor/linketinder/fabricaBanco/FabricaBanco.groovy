package com.igor.linketinder.fabricaBanco

import com.igor.linketinder.database.BancoDados

abstract class FabricaBanco {
    private static FabricaBanco instancia = null

    protected FabricaBanco() {}

    static FabricaBanco criaInstancia(FabricaBanco fabricaBanco) {
        if (instancia == null) {
            instancia = fabricaBanco
        }
        return instancia
    }

    BancoDados iniciarBancoDeDados() {
        BancoDados bancoDados = criarBanco()
        return bancoDados
    }

    protected abstract BancoDados criarBanco()
}
