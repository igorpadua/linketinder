package main.groovy.entity

import groovy.transform.TypeChecked

@TypeChecked
class Vaga {
    String nome
    String descricao
    String local_vaga
    List<Competencia> competencias

    String toString() {
        return "Vaga: ${nome} - ${descricao} - ${local_vaga} - ${competencias}"
    }
}
