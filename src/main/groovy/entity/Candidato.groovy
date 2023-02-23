package main.groovy.entity


import groovy.transform.Canonical
import groovy.transform.TypeChecked

@TypeChecked
@Canonical
class Candidato {
    String nome
    String email
    String cpf
    int idade
    String cep
    String desc
    ArrayList<Competencia> competencias;
}
