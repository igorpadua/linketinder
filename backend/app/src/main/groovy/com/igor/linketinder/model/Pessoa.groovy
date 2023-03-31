package com.igor.linketinder.model

import groovy.transform.Canonical
import groovy.transform.TypeChecked

@TypeChecked
@Canonical
abstract class Pessoa {
    Integer id
    String nome
    String email
    String pais
    String cep
    String descricao
    String senha
}
