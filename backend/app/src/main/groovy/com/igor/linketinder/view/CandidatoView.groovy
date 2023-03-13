package com.igor.linketinder.view

import com.igor.linketinder.service.CompetenciaService
import groovy.transform.TypeChecked
import com.igor.linketinder.model.Candidato
import com.igor.linketinder.model.Competencia

import java.text.SimpleDateFormat

@TypeChecked
class CandidatoView {

    static String pegaCpf() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o CPF do candidato: ")
        return scanner.nextLine()
    }

    static void adicionadoComSucesso() {
        println("Candidato adicionado com sucesso")
    }

    static void atualizadoComSucesso() {
        println("Candidato atualizado com sucesso")
    }

    static void removidoComSucesso() {
        println("Candidato removido com sucesso")
    }

    static Date pegaNascimento() {
        Scanner scanner = new Scanner(System.in)
        print("Digite a data de nascimento do candidato: ")
        String nascimento = scanner.nextLine()
        try {
            Date data = new SimpleDateFormat("dd/MM/yyyy").parse(nascimento)
            return data
        } catch (Exception ignored) {
            println("Data inválida")
            return pegaNascimento()
        }
    }

    static Candidato cadastro() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o nome do novo candidato: ")
        final String nome = scanner.nextLine()
        print("Digite o sobrenome do novo candidato: ")
        final String sobrenome = scanner.nextLine()
        Date nascimento = pegaNascimento()
        print("Digite o email do candidato: ")
        final String email = scanner.nextLine()
        print("Digite o CPF do candidato: ")
        final String cpf = scanner.nextLine()
        print("Digite o país do candidato: ")
        final String pais = scanner.nextLine()
        print("Digite o CEP do candidato: ")
        final String cep = scanner.nextLine()
        print("Digite uma descrição do candidato: ")
        final String desc = scanner.nextLine()
        print("Digite a senha do candidato: ")
        final String senha = scanner.nextLine()

        List<Competencia> competencias = CompetenciaService.escolherCompetencias()

        return new Candidato(nome, sobrenome, nascimento, email, cpf, pais, cep, desc, senha,competencias)
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

    static void atualiza(Candidato candidato) {
        boolean finalizarAtualizacao = true
        Scanner scanner = new Scanner(System.in)
        while (finalizarAtualizacao) {
            menuAtualizar()
            String opc = scanner.nextLine()
            switch (opc) {
                case '1':
                    print("Digite o novo nome: ")
                    final String nome = scanner.nextLine()
                    candidato.nome = nome
                    break
                case '2':
                    print("Digite o novo sobrenome: ")
                    final String sobrenome = scanner.nextLine()
                    candidato.sobrenome = sobrenome
                    break
                case '3':
                    final Date nascimento = pegaNascimento()
                    candidato.nascimento = nascimento
                    break
                case '4':
                    print("Digite o novo email: ")
                    final String email = scanner.nextLine()
                    candidato.email = email
                    break
                case '5':
                    print("Digite o novo país: ")
                    final String pais = scanner.nextLine()
                    candidato.pais = pais
                    break
                case '6':
                    print("Digite o novo CEP: ")
                    final String cep = scanner.nextLine()
                    candidato.cep = cep
                    break
                case '7':
                    print("Digite a nova descrição: ")
                    final String desc = scanner.nextLine()
                    candidato.descricao = desc
                    break
                case '8':
                    print("Digite a nova senha: ")
                    final String senha = scanner.nextLine()
                    candidato.senha = senha
                    break
                case '9':
                    candidato.competencias = CompetenciaService.escolherCompetencias()
                    break
                case '10':
                    finalizarAtualizacao = false
                    break
                default:
                    println("Opção incorreta")
            }
        }
    }


}
