package com.igor.linketinder.model

import groovy.transform.Canonical
import groovy.transform.TypeChecked

@TypeChecked
@Canonical
class Vaga {
    int id
    String nome
    String descricao
    String local_vaga
    Competencia competencia

    String toString() {
        return "Vaga: ${id} - ${nome} - ${descricao} - ${local_vaga} - ${competencia.competencias}"
    }
}
