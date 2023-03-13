package com.igor.linketinder.util

import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class ValidacoesTest {
    @Test
    void validaCPFTest() {
        String cpf = "000.000.000-00"
        Boolean resul = Validacoes.validaCPF(cpf)
        assertEquals(true, resul)
    }

    @Test
    void cpfInvalidoLetraTest() {
        String cpf = "000.000.000-0A"
        Boolean resul = Validacoes.validaCPF(cpf)
        assertEquals(false, resul)
    }

    @Test
    void cpfInvalidoPequenoTest() {
        String cpf = "000.000.000-0"
        Boolean resul = Validacoes.validaCPF(cpf)
        assertEquals(false, resul)
    }

    @Test
    void cpfNulo() {
        String cpf = null
        Boolean resul = Validacoes.validaCPF(cpf)
        assertEquals(false, resul)
    }

    @Test
    void cnpjValidoTest() {
        String cnpj = "50.000.000/0000-00"
        Boolean resul = Validacoes.validaCNPJ(cnpj)
        assertEquals(true, resul)
    }

    @Test
    void cnpjNulo() {
        String cnpj = null
        Boolean resul = Validacoes.validaCNPJ(cnpj)
        assertEquals(false, resul)
    }

    @Test
    void cnpjInvalidoLetraTest() {
        String cnpj = "50.000.000/0000-0A"
        Boolean resul = Validacoes.validaCNPJ(cnpj)
        assertEquals(false, resul)
    }

    @Test
    void cnpjPequenoTest() {
        String cnpj = "50.000.000/0000-0"
        Boolean resul = Validacoes.validaCNPJ(cnpj)
        assertEquals(false, resul)
    }

    @Test
    void validacepTest() {
        String cep = "00000-000"
        Boolean resul = Validacoes.validaCEP(cep)
        assertEquals(true, resul)
    }

    @Test
    void cepInvalidoLetraTest() {
        String cep = "00000-0A0"
        Boolean resul = Validacoes.validaCEP(cep)
        assertEquals(false, resul)
    }

    @Test
    void cepInvalidoPequenoTest() {
        String cep = "0000-00"
        Boolean resul = Validacoes.validaCEP(cep)
        assertEquals(false, resul)
    }

    @Test
    void cepNulo() {
        String cep = null
        Boolean resul = Validacoes.validaCEP(cep)
        assertEquals(false, resul)
    }
}
