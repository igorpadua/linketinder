package com.igor.linketinder

import com.igor.linketinder.controller.CandidatoController
import com.igor.linketinder.controller.EmpresaController
import com.igor.linketinder.controller.VagaController

class App {
    static private void menu() {
        println("1 - Adicionar um novo candidato")
        println("2 - Adicionar uma nova empresa")
        println("3 - Adicionar uma nova vaga")
        println("4 - Atualizar um candidato")
        println("5 - Atualizar uma empresa")
        println("6 - Atualizar uma vaga")
        println("7 - Remover um candidato")
        println("8 - Remover uma empresa")
        println("9 - Remover uma vaga")
        println("10 - Listar candidatos")
        println("11 - Listar empresas")
        println("12 - Listar vagas")
        println("13 - Sair")
    }

    static void main(String[] args) {
        Boolean finalizaMenuPrincipal = true
        Scanner scanner = new Scanner(System.in)

        while (finalizaMenuPrincipal) {
            menu()
            int opc = scanner.nextInt()
            switch (opc) {
                case 1 :
                    CandidatoController.adicionar()
                    break
                case 2:
                    EmpresaController.adicionar()
                    break
                case 3:
                    VagaController.adicionar()
                    break
                case 4:
                    CandidatoController.atualizar()
                    break
                case 5:
                    EmpresaController.atualizar()
                    break
                case 6:
                    VagaController.atualizar()
                    break
                case 7:
                    CandidatoController.remover()
                    break
                case 8:
                    EmpresaController.remover()
                    break
                case 9:
                    VagaController.remover()
                    break
                case 10:
                    CandidatoController.listar()
                    break
                case 11:
                    EmpresaController.listar()
                    break
                case 12:
                    VagaController.listar()
                    break
                case 13:
                    finalizaMenuPrincipal = false
                    println("Saiu com sucesso")
                    break
                default:
                    println("Opção incorreta")
            }
        }
    }
}
