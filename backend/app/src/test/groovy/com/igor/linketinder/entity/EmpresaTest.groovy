package com.igor.linketinder.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EmpresaTest {

    Empresa empresa

    @BeforeEach
    void setup() {
        empresa = new Empresa("Empresa", "empresa@gmail.com", '00000000000000', "Brasil", "12345678", "Sou uma empresa legal", "123456")
    }

    @Test
    void getNomeTest() {
        Assertions.assertEquals("Empresa", empresa.nome)
    }

    @Test
    void getEmailTest() {
        Assertions.assertEquals("empresa@gmail.com", empresa.email)
    }

    @Test
    void getCnpjTest() {
        Assertions.assertEquals('00000000000000', empresa.cnpj)
    }

    @Test
    void getPaisTest() {
        Assertions.assertEquals("Brasil", empresa.pais)
    }

    @Test
    void getCepTest() {
        Assertions.assertEquals("12345678", empresa.cep)
    }

    @Test
    void getDescTest() {
        Assertions.assertEquals("Sou uma empresa legal", empresa.descricao)
    }

    @Test
    void getSenhaTest() {
        Assertions.assertEquals("123456", empresa.senha)
    }

    @Test
    void setNomeTest() {
        empresa.nome = "Empresa 2"
        Assertions.assertEquals("Empresa 2", empresa.nome)
    }

    @Test
    void setEmailTest() {
        empresa.email = "empresa2@gmail.com"
        Assertions.assertEquals("empresa2@gmail.com", empresa.email)
    }

    @Test
    void setCnpjTest() {
        empresa.cnpj = '11111111111111'
        Assertions.assertEquals('11111111111111', empresa.cnpj)
    }

    @Test
    void setPaisTest() {
        empresa.pais = "Brasil 2"
        Assertions.assertEquals("Brasil 2", empresa.pais)
    }

    @Test
    void setCepTest() {
        empresa.cep = "12345679"
        Assertions.assertEquals("12345679", empresa.cep)
    }

    @Test
    void setDescTest() {
        empresa.descricao = "Sou uma empresa legal 2"
        Assertions.assertEquals("Sou uma empresa legal 2", empresa.descricao)
    }

    @Test
    void setSenhaTest() {
        empresa.senha = "1234567"
        Assertions.assertEquals("1234567", empresa.senha)
    }
}
