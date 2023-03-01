package com.igor.linketinder.entity

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

    String toString() {
        return "Nome: ${this.nome} - " +
                "Email: ${this.email} - " +
                "CNPJ: ${this.cnpj} - " +
                "Pais: ${this.pais} - " +
                "CEP: ${this.cep} - " +
                "Descrição: ${this.desc} - " +
                "Senha: ${this.senha}"
    }
}
