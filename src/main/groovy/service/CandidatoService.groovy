package main.groovy.service

import main.groovy.entity.Candidato
import main.groovy.entity.Competencia
import groovy.transform.TypeChecked

import java.text.SimpleDateFormat

@TypeChecked
class CandidatoService {
    static Candidato newCandidato() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o nome do novo candidato: ")
        String nome = scanner.nextLine()
        print("Digite o sobrenome do novo candidato: ")
        String sobrenome = scanner.nextLine()
        print("Digite a data de nascimento do candidato: ")
        String nascimento = scanner.nextLine()
        // Transforma a data de String para Date
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse(nascimento)
        print("Digite o email do candidato: ")
        String email = scanner.nextLine()
        print("Digite o CPF do candidato: ")
        String cpf = scanner.nextLine()
        print("Digite o país do candidato: ")
        String pais = scanner.nextLine()
        print("Digite o CEP  do candidato: ")
        String cep = scanner.nextLine()
        print("Digite uma descrição do candidato: ")
        String desc = scanner.nextLine()
        print("Digite a senha do candidato: ")
        String senha = scanner.nextLine()

        ArrayList<Competencia> competencias = new ArrayList<>()
        competencias = CompetenciaService.choiseCompetencia()

        return new Candidato(nome, sobrenome, data, email, cpf, pais, cep, desc, senha,competencias)
    }

    static void printCandidatos(List<Candidato> pessoas) {

        if (pessoas.isEmpty()) {
            println("Não existe candidatos")
        }

        int i = 0
        for (candidato in pessoas) {
            println("Id: ${i} - " +
                    "Nome: ${candidato.nome} ${candidato.sobrenome} - " +
                    "Data nascimento: ${candidato.nascimento} - " +
                    "Email: ${candidato.email} - " +
                    "CPF: ${candidato.cpf} - " +
                    "Pais: ${candidato.pais} - " +
                    "CEP: ${candidato.cep} - " +
                    "Descrição: ${candidato.desc} - " +
                    "Competencias: ${candidato.competencias}")
            i++
        }
    }

    static String removeCandidato() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o CPF do candidato que deseja remover: ")
        String cpf = scanner.nextLine()
        return cpf
    }

}
