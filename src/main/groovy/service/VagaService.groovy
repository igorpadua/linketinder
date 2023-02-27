package main.groovy.service

import main.groovy.entity.Competencia
import main.groovy.entity.Vaga

class VagaService {

    static Vaga adicionaVaga() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o nome da vaga: ")
        String nome = scanner.nextLine()
        print("Digite a descrição da vaga: ")
        String descricao = scanner.nextLine()
        print("Digite o local da vaga: ")
        String local_vaga = scanner.nextLine()

        List<Competencia> competencias = CompetenciaService.choiseCompetencia()

        new Vaga(nome, descricao, local_vaga , competencias)
    }

    static void printVagas(List<Vaga> vagas) {
        if (vagas.isEmpty()) {
            println("Não existe vagas")
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

    static void atualizarVaga(Vaga vaga) {
        Scanner scanner = new Scanner(System.in)
        boolean sair = true
        while (sair) {
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
                    List<Competencia> competencias = CompetenciaService.choiseCompetencia()
                    vaga.competencias = competencias
                    break
                case '5':
                    sair = false
                    break
                default:
                    println("Opção inválida")
            }
        }
    }

    static String pegaNomeVaga() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o nome da vaga: ")
        String nome = scanner.nextLine()
        return nome
    }
}
