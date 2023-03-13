package com.igor.linketinder.view


import groovy.transform.TypeChecked
import com.igor.linketinder.model.Empresa

import java.util.regex.Pattern

@TypeChecked
class EmpresaView {

    static Boolean validaCNPJ(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) return false
        if (cnpj.length() != 18) return false
        final Pattern cnpjRegex = ~/^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/
        if (!cnpjRegex.matcher(cnpj).matches()) return false
        return true
    }

    static String pegaCnpj() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o CNPJ da empresa: ")
        String cnpj = scanner.nextLine()
        while (!validaCNPJ(cnpj)) {
            println("CNPJ inválido")
            print("Digite o CNPJ da empresa: ")
            cnpj = scanner.nextLine()
        }
        return cnpj
    }

    static Empresa criar() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o nome da nova empresa: ")
        final String nome = scanner.nextLine()
        print("Digite o email da empresa: ")
        final String email = scanner.nextLine()
        final String CNPJ = pegaCnpj()
        print("Digite o pais da empresa: ")
        final String pais = scanner.nextLine()
        final String cep = PessoaView.pegaCep()
        print("Digite uma descrição da empresa: ")
        final String descricao = scanner.nextLine()
        print("Digite a senha da empresa: ")
        final String senha = scanner.nextLine()

        new Empresa(nome, email, CNPJ, pais, cep, descricao, senha)
    }

    static private void menuAtualizar() {
        println("1 - Atualizar nome")
        println("2 - Atualizar email")
        println("3 - Atualizar pais")
        println("4 - Atualizar CEP")
        println("5 - Atualizar descrição")
        println("6 - Atualizar senha")
        println("7 - Sair")
    }

    static void atualizar(Empresa empresa) {
        boolean end = true
        Scanner scanner = new Scanner(System.in)
        while (end) {
            menuAtualizar()
            String opc = scanner.nextLine()
            switch (opc) {
                case '1':
                    print("Digite o novo nome: ")
                    final String nome = scanner.nextLine()
                    empresa.nome = nome
                    break
                case '2':
                    print("Digite o novo email: ")
                    final String email = scanner.nextLine()
                    empresa.email = email
                    break
                case '3':
                    print("Digite o novo pais: ")
                    final String pais = scanner.nextLine()
                    empresa.pais = pais
                    break
                case '4':
                    final String CEP = PessoaView.pegaCep()
                    empresa.cep = CEP
                    break
                case '5':
                    print("Digite a nova descrição: ")
                    final String descricao = scanner.nextLine()
                    empresa.descricao = descricao
                    break
                case '6':
                    print("Digite a nova senha: ")
                    final String senha = scanner.nextLine()
                    empresa.senha = senha
                    break
                case '7':
                    end = false
                    break
                default:
                    println("Opção inválida")
                    break
            }
        }
    }
}
