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

        for (candidato in pessoas) {
            println(candidato)
        }
    }

    static String pegaCpfCandidado() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o CPF do candidato: ")
        String cpf = scanner.nextLine()
        return cpf
    }

    static private void menuAtualizar() {
        println("1 - Atualizar nome")
        println("2 - Atualizar sobrenome")
        println("3 - Atualizar data de nascimento")
        println("4 - Atualizar email")
        println("5 - Atualizar país")
        println("6 - Atualizar CEP")
        println("7 - Atualizar descrição")
        println("8 - Atualizar senha")
        println("9 - Atualizar competências")
        println("10 - Sair")
    }

    static void atualizarCandidato(Candidato candidato) {
        menuAtualizar()
        boolean end = true
        Scanner scanner = new Scanner(System.in)
        String opc = '0'
        while (end) {
            opc = scanner.nextLine()
            switch (opc) {
                case '1':
                    print("Digite o novo nome: ")
                    String nome = scanner.nextLine()
                    candidato.nome = nome
                    break
                case '2':
                    print("Digite o novo sobrenome: ")
                    String sobrenome = scanner.nextLine()
                    candidato.sobrenome = sobrenome
                    break
                case '3':
                    print("Digite a nova data de nascimento: ")
                    String nascimento = scanner.nextLine()
                    Date data = new SimpleDateFormat("dd/MM/yyyy").parse(nascimento)
                    candidato.nascimento = data
                    break
                case '4':
                    print("Digite o novo email: ")
                    String email = scanner.nextLine()
                    candidato.email = email
                    break
                case '5':
                    print("Digite o novo país: ")
                    String pais = scanner.nextLine()
                    candidato.pais = pais
                    break
                case '6':
                    print("Digite o novo CEP: ")
                    String cep = scanner.nextLine()
                    candidato.cep = cep
                    break
                case '7':
                    print("Digite a nova descrição: ")
                    String desc = scanner.nextLine()
                    candidato.desc = desc
                    break
                case '8':
                    print("Digite a nova senha: ")
                    String senha = scanner.nextLine()
                    candidato.senha = senha
                    break
                case '9':
                    candidato.competencias = CompetenciaService.choiseCompetencia()
                    break
                case '10':
                    end = false
                    break
                default:
                    menuAtualizar()
                    println("Opção incorreta")
            }
        }
    }


}
