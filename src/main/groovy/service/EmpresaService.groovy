package main.groovy.service

import main.groovy.entity.Empresa
import groovy.transform.TypeChecked

@TypeChecked
class EmpresaService {
    static Empresa newEmpresa() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o nome da nova empresa: ")
        String nome = scanner.nextLine()
        print("Digite o email da empresa: ")
        String email = scanner.nextLine()
        print("Digite o CNPJ da empresa: ")
        String cnpj = scanner.nextLine()
        print("Digite o pais da empresa: ")
        String pais = scanner.nextLine()
        print("Digite o CEP  da empresa: ")
        String cep = scanner.nextLine()
        print("Digite uma descrição da empresa: ")
        String desc = scanner.nextLine()
        print("Digite a senha da empresa: ")
        String senha = scanner.nextLine()

        new Empresa(nome, email, cnpj, pais, cep, desc, senha)
    }

    static void printEmpresas(List<Empresa> empresas) {

        if (empresas.isEmpty()) {
            println("Não existe candidatos")
        }

        for (empresa in empresas) {
            println(empresa)
        }
    }

    static String pegaCnpjEmpresa() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o CNPJ da empresa: ")
        String cnpj = scanner.nextLine()
        return cnpj
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

    static void atualizarEmpresa(Empresa empresa) {
        boolean end = true
        Scanner scanner = new Scanner(System.in)
        while (end) {
            menuAtualizar()
            String opc = scanner.nextLine()
            switch (opc) {
                case '1':
                    print("Digite o novo nome: ")
                    String nome = scanner.nextLine()
                    empresa.nome = nome
                    break
                case '2':
                    print("Digite o novo email: ")
                    String email = scanner.nextLine()
                    empresa.email = email
                    break
                case '3':
                    print("Digite o novo pais: ")
                    String pais = scanner.nextLine()
                    empresa.pais = pais
                    break
                case '4':
                    print("Digite o novo CEP: ")
                    String cep = scanner.nextLine()
                    empresa.cep = cep
                    break
                case '5':
                    print("Digite a nova descrição: ")
                    String desc = scanner.nextLine()
                    empresa.desc = desc
                    break
                case '6':
                    print("Digite a nova senha: ")
                    String senha = scanner.nextLine()
                    empresa.senha = senha
                    break
                case '7':
                    end = false
                    break
                default:
                    menuAtualizar()
                    println("Opção inválida")
                    break
            }
        }
    }
}
