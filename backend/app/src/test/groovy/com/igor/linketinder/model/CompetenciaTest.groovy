package com.igor.linketinder.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CompetenciaTest {

    @Test
    void constutorTest() {
        Competencia competencia = new Competencia()
        Assertions.assertNotNull(competencia)
    }

    @Test
    void setCompetenciasTest() {
        Competencia competencia = new Competencia()
        competencia.setCompetencias(new ArrayList<>())
        Assertions.assertNotNull(competencia)
    }

    @Test
    void getCompetenciasTest() {
        Competencia competencia = new Competencia()
        competencia.setCompetencias(new ArrayList<>())
        Assertions.assertNotNull(competencia.getCompetencias())
    }

    @Test
    void testTransformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia() {
        String competencias = "{java,python,c}"
        List<TipoCompetencia> resul = Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(competencias)
        Assertions.assertEquals(3, resul.size())
    }

    @Test
    void testTransformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetenciaVazia() {
        String competencias = "{}"
        List<TipoCompetencia> resul = Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(competencias)
        Assertions.assertEquals(0, resul.size())
    }

    @Test
    void testTransformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetenciaComEspaco() {
        String competencias = "{java, python, c}"
        List<TipoCompetencia> resul = Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(competencias)
        Assertions.assertEquals(3, resul.size())
    }

    @Test
    void testTransformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetenciaComoNomeIncorreto() {
        String competencias = "{ JAVA, PYTHON, C }"
        List<TipoCompetencia> resul = Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(competencias)
        Assertions.assertEquals(3, resul.size())
    }
}
