package com.igor.linketinder.service

import com.igor.linketinder.entity.Competencia
import com.igor.linketinder.entity.Vaga

class VagaService {

    static Vaga adicionaVaga() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o nome da vaga: ")
        String nome = scanner.nextLine()
        print("Digite a descrição da vaga: ")
        String descricao = scanner.nextLine()
        print("Digite o local da vaga: ")
        String local_vaga = scanner.nextLine()

        List<Competencia> competencias = CompetenciaService.escolherCompetencias()

        new Vaga(0, nome, descricao, local_vaga , competencias)
    }

    private static Boolean listaEstaValida(List<Vaga> vagas) {
        return vagas != null && !vagas.isEmpty()
    }

    static void printVagas(List<Vaga> vagas) {
        if (!listaEstaValida(vagas)) return

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

    static void atualizarVaga(Vaga vaga) {
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
                    List<Competencia> competencias = CompetenciaService.escolherCompetencias()
                    vaga.competencias = competencias
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
