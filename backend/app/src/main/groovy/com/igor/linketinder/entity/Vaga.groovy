package com.igor.linketinder.entity

import groovy.transform.Canonical
import groovy.transform.TypeChecked

@TypeChecked
@Canonical
class Vaga {
    String nome
    String descricao
    String local_vaga
    List<Competencia> competencias

    String toString() {
        return "Vaga: ${nome} - ${descricao} - ${local_vaga} - ${competencias}"
    }
}
