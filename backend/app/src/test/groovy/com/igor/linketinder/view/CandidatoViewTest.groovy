package com.igor.linketinder.view

import com.igor.linketinder.model.Candidato
import com.igor.linketinder.model.Competencia
import groovy.transform.TypeChecked
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

@TypeChecked
class CandidatoViewTest {
    @Test
    void testAdicionaCandidato() {
        List<Candidato> pessoas = new ArrayList<>()
        pessoas.add(new Candidato('Joao', 'Silva', new Date(2000-04-23), 'joao@gmail.com', '000.000.000-00', 'Brasil', '00000-00', 'Sou um candidato', '123456', [Competencia.cplusplus, Competencia.java]))
        Candidato resul = new Candidato('Joao', 'Silva', new Date(2000-04-23), 'joao@gmail.com', '000.000.000-00', 'Brasil', '00000-00', 'Sou um candidato', '123456', [Competencia.cplusplus, Competencia.java])
        assertEquals(pessoas.get(0), resul)
    }

    @Test
    void validaCPFTest() {
        String cpf = "000.000.000-00"
        Boolean resul = CandidatoView.validaCPF(cpf)
        assertEquals(true, resul)
    }

    @Test
    void cpfInvalidoLetraTest() {
        String cpf = "000.000.000-0A"
        Boolean resul = CandidatoView.validaCPF(cpf)
        assertEquals(false, resul)
    }

    @Test
    void cpfInvalidoPequenoTest() {
        String cpf = "000.000.000-0"
        Boolean resul = CandidatoView.validaCPF(cpf)
        assertEquals(false, resul)
    }

    @Test
    void cpfNulo() {
        String cpf = null
        Boolean resul = CandidatoView.validaCPF(cpf)
        assertEquals(false, resul)
    }
}
