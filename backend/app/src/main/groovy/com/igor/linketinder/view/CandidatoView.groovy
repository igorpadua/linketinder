package com.igor.linketinder.view


import groovy.transform.TypeChecked
import com.igor.linketinder.entity.Candidato
import com.igor.linketinder.entity.Competencia

import java.text.SimpleDateFormat
import java.util.regex.Pattern

@TypeChecked
class CandidatoView {

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

    static Boolean validaCPF(String cpf) {
        if (cpf == null || cpf.isEmpty()) return false
        final Pattern cpfRegex = ~/^\d{3}\.\d{3}\.\d{3}-\d{2}$/
        if (!cpfRegex.matcher(cpf).matches()) return false
        return true
    }

    static String pegaCPF() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o CPF do candidato: ")
        String cpf = scanner.nextLine()
        while (!validaCPF(cpf)) {
            println("CPF inválido")
            print("Digite o CPF do candidato: ")
            cpf = scanner.nextLine()
        }
        return cpf
    }

    static Candidato newCandidato() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o nome do novo candidato: ")
        final String nome = scanner.nextLine()
        print("Digite o sobrenome do novo candidato: ")
        final String sobrenome = scanner.nextLine()
        Date nascimento = pegaNascimento()
        print("Digite o email do candidato: ")
        final String email = scanner.nextLine()
        final String cpf = pegaCPF()
        print("Digite o país do candidato: ")
        final String pais = scanner.nextLine()
        final String cep = PessoaView.pegaCep()
        print("Digite uma descrição do candidato: ")
        final String desc = scanner.nextLine()
        print("Digite a senha do candidato: ")
        final String senha = scanner.nextLine()

        List<Competencia> competencias = CompetenciaView.escolherCompetencias()

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

    static void atualizarCandidato(Candidato candidato) {
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
                    final String cep = PessoaView.pegaCep()
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
                    candidato.competencias = CompetenciaView.escolherCompetencias()
                    break
                case '10':
                    finalizarAtualizacao = false
                    break
                default:
                    menuAtualizar()
                    println("Opção incorreta")
            }
        }
    }


}
