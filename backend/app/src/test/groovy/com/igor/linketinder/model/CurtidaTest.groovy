package com.igor.linketinder.model

import spock.lang.Specification

class CurtidaTest extends Specification {

    Curtida curtida = null

    def setup() {
        curtida = new Curtida(0, 0)
    }

    def "Testa se a curtida foi criado corretamente"() {
        expect:
        curtida.idUsuario == 0
        curtida.idLink == 0
    }

    def "Testa o getIdUsuario"() {
        expect:
        curtida.getIdUsuario() == 0
    }

    def "Testa o getIdLink"() {
        expect:
        curtida.getIdLink() == 0
    }

    def "Test o setIdUsuario"() {
        when:
        curtida.setIdUsuario(1)

        then:
        curtida.getIdUsuario() == 1
    }

    def "Test o setIdLink"() {
        when:
        curtida.setIdLink(1)

        then:
        curtida.getIdLink() == 1
    }
}
