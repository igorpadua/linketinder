package com.igor.linketinder.service

import com.igor.linketinder.model.Competencia
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CompetenciaServiceTest {
    @Test
    void testTransformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia() {
        String competencias = "{java,python,c}"
        List<Competencia> resul = CompetenciaService.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(competencias)
        Assertions.assertEquals(3, resul.size())
    }

    @Test
    void testTransformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetenciaVazia() {
        String competencias = "{}"
        List<Competencia> resul = CompetenciaService.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(competencias)
        Assertions.assertEquals(0, resul.size())
    }

    @Test
    void testTransformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetenciaComEspaco() {
        String competencias = "{java, python, c}"
        List<Competencia> resul = CompetenciaService.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(competencias)
        Assertions.assertEquals(3, resul.size())
    }

    @Test
    void testTransformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetenciaComoNomeIncorreto() {
        String competencias = "{ JAVA, PYTHON, C }"
        List<Competencia> resul = CompetenciaService.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(competencias)
        Assertions.assertEquals(3, resul.size())
    }
}
