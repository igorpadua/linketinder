package main.groovy.entity


import groovy.transform.Canonical
import groovy.transform.TypeChecked

@TypeChecked
@Canonical
class Candidato {
    String nome
    String sobrenome
    Date nascimento
    String email
    String cpf
    String pais
    String cep
    String desc
    String senha
    List<Competencia> competencias;

    String toString() {
        return"Nome: ${this.nome} ${this.sobrenome} - " +
                "Data nascimento: ${this.nascimento} - " +
                "Email: ${this.email} - " +
                "CPF: ${this.cpf} - " +
                "Pais: ${this.pais} - " +
                "CEP: ${this.cep} - " +
                "Descrição: ${this.desc} - " +
                "Competencias: ${this.competencias}"
    }
}
