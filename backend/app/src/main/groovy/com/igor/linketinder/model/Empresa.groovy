package com.igor.linketinder.model

import groovy.transform.Canonical
import groovy.transform.TypeChecked

@TypeChecked
@Canonical
class Empresa extends Pessoa {
    String cnpj

    Empresa(Integer id = 0, String nome, String email, String cnpj, String pais, String cep, String descricao, String senha) {
        super(id, nome, email, pais, cep, descricao, senha)
        this.cnpj = cnpj
    }

    String toString() {
        return "Nome: ${this.nome} - " +
                "Email: ${this.email} - " +
                "CNPJ: ${this.cnpj} - " +
                "Pais: ${this.pais} - " +
                "CEP: ${this.cep} - " +
                "Descrição: ${this.descricao} - " +
                "Senha: ${this.senha}"
    }
}
