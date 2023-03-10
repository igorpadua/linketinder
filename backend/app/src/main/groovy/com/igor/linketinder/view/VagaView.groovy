package com.igor.linketinder.view

import com.igor.linketinder.controller.EmpresaController
import com.igor.linketinder.model.Competencia
import com.igor.linketinder.model.TipoCompetencia
import com.igor.linketinder.model.Empresa
import com.igor.linketinder.model.Vaga

class VagaView {

    static void adicionadoComSucesso() {
        println("Vaga adicionada com sucesso")
    }

    static void atualizadoComSucesso() {
        println("Vaga atualizada com sucesso")
    }

    static void removidoComSucesso() {
        println("Vaga removida com sucesso")
    }

    static Vaga cadastro() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o CNPJ da empresa: ")
        String CNPJ = scanner.nextLine()
        Empresa empresa = EmpresaController.pegaEmpresaPeloCnpj(CNPJ)
        print("Digite o nome da vaga: ")
        String nome = scanner.nextLine()
        print("Digite a descrição da vaga: ")
        String descricao = scanner.nextLine()
        print("Digite o local da vaga: ")
        String local_vaga = scanner.nextLine()

        Competencia competencia = new Competencia()
        List<TipoCompetencia> competencias = CompetenciaView.escolherCompetencias()
        competencia.competencias = competencias


        new Vaga(0, nome, descricao, local_vaga , competencia, empresa)
    }

    static void lista(List<Vaga> vagas) {

        if (vagas.isEmpty()) {
            println("Não há vagas cadastradas")
            return
        }

        for (vaga in vagas) {
            println(vaga)
        }
    }

    private static void menuAtualizar() {
        println("1 - Atualizar nome")
        println("2 - Atualizar descrição")
        println("3 - Atualizar local")
        println("4 - Atualizar competências")
        println("5 - Sair")
        print("Digite a opção desejada: ")
    }

    static void atualiza(Vaga vaga) {
        Scanner scanner = new Scanner(System.in)
        boolean finalizarAtualizacoes = true
        while (finalizarAtualizacoes) {
            menuAtualizar()
            String opcao = scanner.nextLine()
            switch (opcao) {
                case '1':
                    print("Digite o novo nome da vaga: ")
                    String nome = scanner.nextLine()
                    vaga.nome = nome
                    break
                case '2':
                    print("Digite a nova descrição da vaga: ")
                    String descricao = scanner.nextLine()
                    vaga.descricao = descricao
                    break
                case '3':
                    print("Digite o novo local da vaga: ")
                    String local_vaga = scanner.nextLine()
                    vaga.local_vaga = local_vaga
                    break
                case '4':
                    List<TipoCompetencia> competencias = CompetenciaView.escolherCompetencias()
                    vaga.competencia.competencias = competencias
                    break
                case '5':
                    finalizarAtualizacoes = false
                    break
                default:
                    println("Opção inválida")
            }
        }
    }

    static int pegaID() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o id da vaga: ")
        int id = scanner.nextInt()
        return id
    }
}
