package com.igor.linketinder.view


import com.igor.linketinder.view.PessoaView
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class PessoaViewTest {

    @Test
    void validacepTest() {
        String cep = "00000-000"
        Boolean resul = PessoaView.validaCEP(cep)
        assertEquals(true, resul)
    }

    @Test
    void cepInvalidoLetraTest() {
        String cep = "00000-0A0"
        Boolean resul = PessoaView.validaCEP(cep)
        assertEquals(false, resul)
    }

    @Test
    void cepInvalidoPequenoTest() {
        String cep = "0000-00"
        Boolean resul = PessoaView.validaCEP(cep)
        assertEquals(false, resul)
    }

    @Test
    void cepNulo() {
        String cep = null
        Boolean resul = PessoaView.validaCEP(cep)
        assertEquals(false, resul)
    }
}
