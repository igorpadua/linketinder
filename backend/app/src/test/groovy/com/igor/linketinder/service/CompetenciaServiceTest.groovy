package com.igor.linketinder.service

import com.igor.linketinder.entity.Competencia
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CompetenciaServiceTest {

    @Test
    void TransformaStringPython() {
        String competencia = "Python"
        Assertions.assertEquals(CompetenciaService.transformaString(competencia), Competencia.Python)
    }

    @Test
    void TransformaStringJava() {
        String competencia = "Java"
        Assertions.assertEquals(CompetenciaService.transformaString(competencia), Competencia.Java)
    }

    @Test
    void TransformaStringSpringFramework() {
        String competencia = "SpringFramework"
        Assertions.assertEquals(CompetenciaService.transformaString(competencia), Competencia.SpringFramework)
    }

    @Test
    void TransformaStringAngular() {
        String competencia = "Angular"
        Assertions.assertEquals(CompetenciaService.transformaString(competencia), Competencia.Angular)
    }

    @Test
    void TransformaStringCplusplus() {
        String competencia = "cplusplus"
        Assertions.assertEquals(CompetenciaService.transformaString(competencia), Competencia.cplusplus)
    }

    @Test
    void TransformaStringC() {
        String competencia = "c"
        Assertions.assertEquals(CompetenciaService.transformaString(competencia), Competencia.c)
    }

    @Test
    void TransformaStringJavaScript() {
        String competencia = "JavaScript"
        Assertions.assertEquals(CompetenciaService.transformaString(competencia), Competencia.JavaScript)
    }

    @Test
    void TransformaStringHtml() {
        String competencia = "Html"
        Assertions.assertEquals(CompetenciaService.transformaString(competencia), Competencia.Html)
    }

    @Test
    void TransformaStringNode() {
        String competencia = "Node"
        Assertions.assertEquals(CompetenciaService.transformaString(competencia), Competencia.Node)
    }

    @Test
    void TransformaStringIncorreta() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String competencia = "Incorreta"
            CompetenciaService.transformaString(competencia)
        })
    }

}
