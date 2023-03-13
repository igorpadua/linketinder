package com.igor.linketinder.view


import groovy.transform.TypeChecked
import com.igor.linketinder.model.Empresa

@TypeChecked
class EmpresaView {

    static Empresa criar() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o nome da nova empresa: ")
        final String nome = scanner.nextLine()
        print("Digite o email da empresa: ")
        final String email = scanner.nextLine()
        print("Digite o CNPJ da empresa: ")
        final String CNPJ = scanner.nextLine()
        print("Digite o pais da empresa: ")
        final String pais = scanner.nextLine()
        print("Digite o CEP da empresa: ")
        final String cep = scanner.nextLine()
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
                    print("Digite o novo CEP: ")
                    final String CEP = scanner.nextLine()
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
