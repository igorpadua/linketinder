package main.groovy.entity

import groovy.transform.Canonical
import groovy.transform.TypeChecked

@TypeChecked
@Canonical
class Empresa {
    String nome
    String email
    String cnpj
    String pais
    String cep
    String desc
    String senha
}
