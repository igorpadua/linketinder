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
}
