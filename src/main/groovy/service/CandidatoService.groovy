package main.groovy.service

import main.groovy.entity.Candidato
import main.groovy.entity.Competencia
import main.groovy.entity.CompetenciaAbstract
import groovy.transform.TypeChecked

@TypeChecked
class CandidatoService {
    static Candidato newCandidato() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o nome do novo candidato: ")
        String nome = scanner.nextLine()
        print("Digite o email do candidato: ")
        String email = scanner.nextLine()
        print("Digite o CPF do candidato: ")
        String cpf = scanner.nextLine()
        print("Digite a idade do candidato: ")
        int idade = scanner.nextInt()
        scanner = new Scanner(System.in)
        print("Digite o CEP  do candidato: ")
        String cep = scanner.nextLine()
        print("Digite uma descrição do candidato: ")
        String desc = scanner.nextLine()

        ArrayList<Competencia> aux = new ArrayList<>()
        aux = CompetenciaAbstract.choiseCompetencia()

        new Candidato(nome, email, cpf, idade, cep, desc, aux)
    }

    static void printCandidatos(List<Candidato> pessoas) {

        if (pessoas.isEmpty()) {
            println("Não existe candidatos")
        }

        for (candidatos in pessoas) {
            println(candidatos)
        }
    }

}
