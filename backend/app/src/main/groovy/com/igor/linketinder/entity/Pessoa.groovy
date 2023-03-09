package com.igor.linketinder.entity

import groovy.transform.Canonical
import groovy.transform.TypeChecked

@TypeChecked
@Canonical
abstract class Pessoa {
    String nome
    String email
    String pais
    String cep
    String descricao
    String senha
}
