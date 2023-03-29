package com.igor.linketinder.model

import spock.lang.Specification

class EmpresaTest extends Specification {

    Empresa empresa

    def setup() {
        empresa = new Empresa("Empresa", "empresa@gmail.com", '00000000000000', "Brasil", "12345678", "Sou uma empresa legal", "123456")
    }

    def "Testa se a empresa foi criada corretamente"() {
        expect:
        empresa.nome == "Empresa"
        empresa.email == "empresa@gmail.com"
        empresa.cnpj == '00000000000000'
        empresa.pais == "Brasil"
        empresa.cep == "12345678"
        empresa.descricao == "Sou uma empresa legal"
    }

    def "Testa o getNome"() {
        expect:
        empresa.getNome() == "Empresa"
    }

    def "Testa o getEmail"() {
        expect:
        empresa.getEmail() == "empresa@gmail.com"
    }

    def "Testa o getCnpj"() {
        expect:
        empresa.getCnpj() == '00000000000000'
    }

    def "Testa o getPais"() {
        expect:
        empresa.getPais() == "Brasil"
    }

    def "Testa o getCep"() {
        expect:
        empresa.getCep() == "12345678"
    }

    def "Testa o getDescricao"() {
        expect:
        empresa.getDescricao() == "Sou uma empresa legal"
    }

    def "Testa o getSenha"() {
        expect:
        empresa.getSenha() == "123456"
    }

    def "Testa o setNome"() {
        when:
        empresa.setNome("Empresa 2")
        then:
        empresa.getNome() == "Empresa 2"
    }

    def "Testa o setEmail"() {
        when:
        empresa.setEmail("empresa2@gmail.com")

        then:
        empresa.getEmail() == "empresa2@gmail.com"
    }

    def "Testa o setCnpj"() {
        when:
        empresa.setCnpj('11111111111111')
        then:
        empresa.getCnpj() == '11111111111111'
    }

    def "Testa o setPais"() {
        when:
        empresa.setPais("Brasil 2")
        then:
        empresa.getPais() == "Brasil 2"
    }

    def "Testa o setCep"() {
        when:
        empresa.setCep("12345679")
        then:
        empresa.getCep() == "12345679"
    }

    def "Testa o setDescricao"() {
        when:
        empresa.setDescricao("Sou uma empresa legal 2")
        then:
        empresa.getDescricao() == "Sou uma empresa legal 2"
    }

    def "Testa o setSenha"() {
        when:
        empresa.setSenha("1234567")
        then:
        empresa.getSenha() == "1234567"
    }
}
