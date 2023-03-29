package com.igor.linketinder.model

import spock.lang.Specification


class CompetenciaTest extends Specification {

    Competencia competencia = null

    def setup() {
        competencia = new Competencia()
        competencia.competencias = [TipoCompetencia.angular, TipoCompetencia.cplusplus]
    }

    def "Testa se a competencia foi criada corretamente"() {
        expect:
        competencia.competencias == [TipoCompetencia.angular, TipoCompetencia.cplusplus]
    }

    def "Testa o getCompetencias"() {
        expect:
        competencia.getCompetencias() == [TipoCompetencia.angular, TipoCompetencia.cplusplus]
    }

    def "Testa o setCompetencias"() {
        when:
        competencia.setCompetencias([TipoCompetencia.java, TipoCompetencia.python])

        then:
        competencia.getCompetencias() == [TipoCompetencia.java, TipoCompetencia.python]
    }

    def "Testa transformar uma string de competencias em uma lista de competencias"() {
        when:
        List<TipoCompetencia> competencias = Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia("{Java, c, angular}")

        then:
        competencias == [TipoCompetencia.java, TipoCompetencia.c, TipoCompetencia.angular]
    }

    def "Testa transformar uma string de competencias em uma lista de competencias com uma competencia invalida"() {
        when:
        Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia("{Java, c, angular, teste}")

        then:
        thrown(RuntimeException)
    }

    def "Testa transformar uma string de competencias em uma lista de competencias com uma competencia vazia"() {
        when:
        List<TipoCompetencia> competencias = Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia("{Java, c, angular, }")

        then:
        competencias == [TipoCompetencia.java, TipoCompetencia.c, TipoCompetencia.angular]
    }

    def "Testa transformar uma string de competencias em uma lista de competencias com uma competencia vazia no meio"() {
        when:
        List<TipoCompetencia> competencias = Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia("{Java, c, , angular}")

        then:
        competencias == [TipoCompetencia.java, TipoCompetencia.c, TipoCompetencia.angular]
    }
    
}
