package com.igor.linketinder.service

import com.igor.linketinder.entity.Candidato
import com.igor.linketinder.entity.Competencia
import groovy.transform.TypeChecked
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

@TypeChecked
class CandidatoServiceTest {
    @Test
    void testAdicionaCandidato() {
        List<Candidato> pessoas = new ArrayList<>()
        pessoas.add(new Candidato('Joao', 'Silva', new Date(2000-04-23), 'joao@gmail.com', '000.000.000-00', 'Brasil', '00000-00', 'Sou um candidato', '123456', [Competencia.cplusplus, Competencia.Java]))
        Candidato resul = new Candidato('Joao', 'Silva', new Date(2000-04-23), 'joao@gmail.com', '000.000.000-00', 'Brasil', '00000-00', 'Sou um candidato', '123456', [Competencia.cplusplus, Competencia.Java])
        assertEquals(pessoas.get(0), resul)
    }

    @Test
    void validaCPFTest() {
        String cpf = "000.000.000-00"
        Boolean resul = CandidatoService.validaCPF(cpf)
        assertEquals(true, resul)
    }

    @Test
    void cpfInvalidoLetraTest() {
        String cpf = "000.000.000-0A"
        Boolean resul = CandidatoService.validaCPF(cpf)
        assertEquals(false, resul)
    }

    @Test
    void cpfInvalidoPequenoTest() {
        String cpf = "000.000.000-0"
        Boolean resul = CandidatoService.validaCPF(cpf)
        assertEquals(false, resul)
    }

    @Test
    void cpfNulo() {
        String cpf = null
        Boolean resul = CandidatoService.validaCPF(cpf)
        assertEquals(false, resul)
    }
}