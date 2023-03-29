package com.igor.linketinder.model

import spock.lang.Specification

import java.text.SimpleDateFormat


class CandidatoTest extends Specification {

    Candidato candidato = null

    def setup() {
        Competencia competencia = new Competencia()
        competencia.competencias = [TipoCompetencia.angular, TipoCompetencia.cplusplus]
        Date nascimento = new SimpleDateFormat("dd/MM/yyyy").parse("28/02/1995")
        candidato = new Candidato("João", "Silva", nascimento, "joao@gmail.com", "000.000.000-00", "Brasil",
                "00000-000", "Desenvolvedor", "12345", competencia)
    }

    def "Testa se o candidato foi criado corretamente"() {
        expect:
        candidato.nome == "João"
        candidato.sobrenome == "Silva"
        candidato.nascimento == new SimpleDateFormat("dd/MM/yyyy").parse("28/02/1995")
        candidato.email == "joao@gmail.com"
        candidato.cpf == "000.000.000-00"
        candidato.pais == "Brasil"
        candidato.cep == "00000-000"
        candidato.descricao == "Desenvolvedor"
        candidato.senha == "12345"
        candidato.competencia.competencias == [TipoCompetencia.angular, TipoCompetencia.cplusplus]
    }

    def "Testa o getNome"() {
        expect:
        candidato.getNome() == "João"
    }

    def "Testa o getSobrenome"() {
        expect:
        candidato.getSobrenome() == "Silva"
    }

    def "Testa o getNascimento"() {
        expect:
        candidato.getNascimento() == new SimpleDateFormat("dd/MM/yyyy").parse("28/02/1995")
    }

    def "Testa o getEmail"() {
        expect:
        candidato.getEmail() == "joao@gmail.com"
    }

    def "Testa o getCpf"() {
        expect:
        candidato.getCpf() == "000.000.000-00"
    }

    def "Testa o getPais"() {
        expect:
        candidato.getPais() == "Brasil"
    }

    def "Testa o getCep"() {
        expect:
        candidato.getCep() == "00000-000"
    }

    def "Testa o getDescricao"() {
        expect:
        candidato.getDescricao() == "Desenvolvedor"
    }

    def "Testa o getSenha"() {
        expect:
        candidato.getSenha() == "12345"
    }

    def "Testa o getCompetencia com a lista de competencias"() {
        expect:
        candidato.getCompetencia().competencias == [TipoCompetencia.angular, TipoCompetencia.cplusplus]
    }

    def "Testa o setNome"() {
        when:
        candidato.setNome("Paulo")
        then:
        candidato.getNome() == "Paulo"
    }

    def "Testa o setSobrenome"() {
        when:
        candidato.setSobrenome("Santos")
        then:
        candidato.getSobrenome() == "Santos"
    }

    def "Testa o setNascimento"() {
        when:
        candidato.setNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("28/02/1996"))
        then:
        candidato.getNascimento() == new SimpleDateFormat("dd/MM/yyyy").parse("28/02/1996")
    }

    def "Testa o setEmail"() {
        when:
        candidato.setEmail("paulo@gmail.com")
        then:
        candidato.getEmail() == "paulo@gmail.com"
    }

    def "Testa o setCpf"() {
        when:
        candidato.setCpf("111.111.111-11")
        then:
        candidato.getCpf() == "111.111.111-11"
    }

    def "Testa o setPais"() {
        when:
        candidato.setPais("Argentina")
        then:
        candidato.getPais() == "Argentina"
    }

    def "Testa o setCep"() {
        when:
        candidato.setCep("11111-111")
        then:
        candidato.getCep() == "11111-111"
    }

    def "Testa o setDescricao"() {
        when:
        candidato.setDescricao("Desenvolvedor de software")
        then:
        candidato.getDescricao() == "Desenvolvedor de software"
    }

    def "Testa o setSenha"() {
        when:
        candidato.setSenha("54321")
        then:
        candidato.getSenha() == "54321"
    }

    def "Testa o setCompetencia das listas com competencias"() {
        when:
        Competencia competencia = new Competencia()
        competencia.competencias = [TipoCompetencia.angular, TipoCompetencia.cplusplus, TipoCompetencia.java]
        candidato.setCompetencia(competencia)
        then:
        candidato.getCompetencia().competencias == [TipoCompetencia.angular, TipoCompetencia.cplusplus, TipoCompetencia.java]
    }
}