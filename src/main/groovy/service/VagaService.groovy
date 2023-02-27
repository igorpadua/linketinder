package main.groovy.service

import main.groovy.entity.Vaga

class VagaService {

    static Vaga adicionaVaga() {
        Scanner scanner = new Scanner(System.in)
        println("Digite o nome da vaga: ")
        String nome = scanner.nextLine()
        println("Digite a descrição da vaga: ")
        String descricao = scanner.nextLine()
        println("Digite o local da vaga: ")
        String local_vaga = scanner.nextLine()

        new Vaga(nome, descricao, local_vaga)
    }
}
