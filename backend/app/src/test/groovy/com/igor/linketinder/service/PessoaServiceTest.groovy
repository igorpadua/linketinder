package com.igor.linketinder.service

import com.igor.linketinder.entity.Pessoa
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class PessoaServiceTest {

    @Test
    void validacepTest() {
        String cep = "00000-000"
        Boolean resul = PessoaService.validaCEP(cep)
        assertEquals(true, resul)
    }

    @Test
    void cepInvalidoLetraTest() {
        String cep = "00000-0A0"
        Boolean resul = PessoaService.validaCEP(cep)
        assertEquals(false, resul)
    }

    @Test
    void cepInvalidoPequenoTest() {
        String cep = "0000-00"
        Boolean resul = PessoaService.validaCEP(cep)
        assertEquals(false, resul)
    }

    @Test
    void cepNulo() {
        String cep = null
        Boolean resul = PessoaService.validaCEP(cep)
        assertEquals(false, resul)
    }
}
