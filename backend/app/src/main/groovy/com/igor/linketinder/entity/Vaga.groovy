package com.igor.linketinder.entity

import groovy.transform.Canonical
import groovy.transform.TypeChecked

@TypeChecked
@Canonical
class Vaga {
    int id
    String nome
    String descricao
    String local_vaga
    List<Competencia> competencias

    String toString() {
        return "Vaga: ${id} - ${nome} - ${descricao} - ${local_vaga} - ${competencias}"
    }
}
