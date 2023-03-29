package com.igor.linketinder.model

import spock.lang.Specification

class VagaTest extends Specification {

    Vaga vaga = null

    def setup() {
        Competencia competencia = new Competencia()
        competencia.competencias = [TipoCompetencia.cplusplus, TipoCompetencia.html]
        vaga = new Vaga(0, "Vaga de Teste", "Descrição da Vaga de Teste", "Local da Vaga de Teste", competencia)
    }

    def "Testa se a vaga foi criado corretamente"() {
        expect:
        vaga.id == 0
        vaga.nome == "Vaga de Teste"
        vaga.descricao == "Descrição da Vaga de Teste"
        vaga.local_vaga == "Local da Vaga de Teste"
        vaga.competencia.competencias == [TipoCompetencia.cplusplus, TipoCompetencia.html]
    }

    def "Testa o getId"() {
        expect:
        vaga.getId() == 0
    }

    def "Testa o getNome"() {
        expect:
        vaga.getNome() == "Vaga de Teste"
    }

    def "Testa o getDescricao"() {
        expect:
        vaga.getDescricao() == "Descrição da Vaga de Teste"
    }

    def "Testa o getLocal_vaga"() {
        expect:
        vaga.getLocal_vaga() == "Local da Vaga de Teste"
    }

    def "Testa o getCompetencia"() {
        expect:
        vaga.getCompetencia() == new Competencia([TipoCompetencia.cplusplus, TipoCompetencia.html])
    }

}
