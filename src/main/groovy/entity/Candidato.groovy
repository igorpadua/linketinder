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
    ArrayList<Competencia> competencias;
}
