package com.igor.linketinder.view

import com.igor.linketinder.model.Pessoa

import java.util.regex.Pattern

class PessoaView {

    static void lista(List<Pessoa> pessoas) {
        if (pessoas.isEmpty()) {
            println("Nenhuma pessoa cadastrada")
            return
        }

        for (pessoa in pessoas) {
            println(pessoa)
        }
    }
}
