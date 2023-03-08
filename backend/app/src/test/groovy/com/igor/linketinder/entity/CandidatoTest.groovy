package com.igor.linketinder.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CandidatoTest {

    private Candidato candidato

    @BeforeEach
    void setup() {
        candidato = new Candidato("João", "Silva", new Date(1998 - 10 - 10),
                "joao@gmail.com", "123456789", "Brasil", "12345678", "Sou um cara legal",
                "123456", [Competencia.Java, Competencia.JavaScript])
    }

    @Test
    void getNomeTest() {
        Assertions.assertEquals("João", candidato.nome)
    }

    @Test
    void getSobrenomeTest() {
        Assertions.assertEquals("Silva", candidato.sobrenome)
    }

    @Test
    void getNascimentoTest() {
        Assertions.assertEquals(new Date(1998 - 10 - 10), candidato.nascimento)
    }

    @Test
    void getEmailTest() {
        Assertions.assertEquals("joao@gmail.com", candidato.email)
    }

    @Test
    void getCpfTest() {
        Assertions.assertEquals("123456789", candidato.cpf)
    }

    @Test
    void getPaisTest() {
        Assertions.assertEquals("Brasil", candidato.pais)
    }

    @Test
    void getCepTest() {
        Assertions.assertEquals("12345678", candidato.cep)
    }

    @Test
    void getDescTest() {
        Assertions.assertEquals("Sou um cara legal", candidato.desc)
    }

    @Test
    void getSenhaTest() {
        Assertions.assertEquals("123456", candidato.senha)
    }

    @Test
    void getCompetenciasTest() {
        Assertions.assertEquals([Competencia.Java, Competencia.JavaScript], candidato.competencias)
    }

    @Test
    void setNomeTest() {
        candidato.nome = "Maria"
        Assertions.assertEquals("Maria", candidato.nome)
    }

    @Test
    void setSobrenomeTest() {
        candidato.sobrenome = "Santos"
        Assertions.assertEquals("Santos", candidato.sobrenome)
    }

    @Test
    void setNascimentoTest() {
        candidato.nascimento = new Date(1998 - 10 - 10)
        Assertions.assertEquals(new Date(1998 - 10 - 10), candidato.nascimento)
    }

    @Test
    void setEmailTest() {
        candidato.email = "maria@gmail.com"
        Assertions.assertEquals("maria@gmail.com", candidato.email)
    }

    @Test
    void setCpfTest() {
        candidato.cpf = "987654321"
        Assertions.assertEquals("987654321", candidato.cpf)
    }

    @Test
    void setPaisTest() {
        candidato.pais = "EUA"
        Assertions.assertEquals("EUA", candidato.pais)
    }

    @Test
    void setCepTest() {
        candidato.cep = "87654321"
        Assertions.assertEquals("87654321", candidato.cep)
    }

    @Test
    void setDescTest() {
        candidato.desc = "Sou uma pessoa legal"
        Assertions.assertEquals("Sou uma pessoa legal", candidato.desc)
    }

    @Test
    void setSenhaTest() {
        candidato.senha = "654321"
        Assertions.assertEquals("654321", candidato.senha)
    }

    @Test
    void setCompetenciasTest() {
        candidato.competencias = [Competencia.Java, Competencia.JavaScript, Competencia.Python]
        Assertions.assertEquals([Competencia.Java, Competencia.JavaScript, Competencia.Python], candidato.competencias)
    }

}