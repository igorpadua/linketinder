package com.igor.linketinder.util

import spock.lang.Specification


class ValidacoesTest extends Specification {

    def "Testa se o cpf é valido"() {
        expect:
        Validacoes.validaCPF("000.000.000-00") == true
    }

    def "Testa se o cpf é invalido com uma letra"() {
        expect:
        Validacoes.validaCPF("000.000.000-0A") == false
    }

    def "Testa se o cpf é invalido falta um numero"() {
        expect:
        Validacoes.validaCPF("000.000.000-0") == false
    }

    def "Testa se o cpf é nulo"() {
        expect:
        Validacoes.validaCPF(null) == false
    }

    def "Testa se o cnpj é valido"() {
        expect:
        Validacoes.validaCNPJ("50.000.000/0000-00") == true
    }

    def "Testa se o cnpj é nulo"() {
        expect:
        Validacoes.validaCNPJ(null) == false
    }

    def "Testa se o cnpj é invalido com uma letra"() {
        expect:
        Validacoes.validaCNPJ("50.000.000/0000-0A") == false
    }

    def "Testa se o cnpj é invalido falta um numero"() {
        expect:
        Validacoes.validaCNPJ("50.000.000/0000-0") == false
    }

    def "Testa se o cep é valido"() {
        expect:
        Validacoes.validaCEP("00000-000") == true
    }

    def "Testa se o cep é invalido com uma letra"() {
        expect:
        Validacoes.validaCEP("00000-0A0") == false
    }

    def "Testa se o cep é invalido falta um numero"() {
        expect:
        Validacoes.validaCEP("00000-00") == false
    }

    def "Testa se o cep é nulo"() {
        expect:
        Validacoes.validaCEP(null) == false
    }
}
