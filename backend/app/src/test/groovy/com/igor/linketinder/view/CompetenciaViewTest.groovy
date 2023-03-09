package com.igor.linketinder.view

import com.igor.linketinder.entity.Competencia
import com.igor.linketinder.view.CompetenciaView
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CompetenciaViewTest {

    @Test
    void TransformaStringPython() {
        String competencia = "Python"
        Assertions.assertEquals(CompetenciaView.transformaString(competencia), Competencia.Python)
    }

    @Test
    void TransformaStringJava() {
        String competencia = "Java"
        Assertions.assertEquals(CompetenciaView.transformaString(competencia), Competencia.Java)
    }

    @Test
    void TransformaStringSpringFramework() {
        String competencia = "SpringFramework"
        Assertions.assertEquals(CompetenciaView.transformaString(competencia), Competencia.SpringFramework)
    }

    @Test
    void TransformaStringAngular() {
        String competencia = "Angular"
        Assertions.assertEquals(CompetenciaView.transformaString(competencia), Competencia.Angular)
    }

    @Test
    void TransformaStringCplusplus() {
        String competencia = "cplusplus"
        Assertions.assertEquals(CompetenciaView.transformaString(competencia), Competencia.cplusplus)
    }

    @Test
    void TransformaStringC() {
        String competencia = "c"
        Assertions.assertEquals(CompetenciaView.transformaString(competencia), Competencia.c)
    }

    @Test
    void TransformaStringJavaScript() {
        String competencia = "JavaScript"
        Assertions.assertEquals(CompetenciaView.transformaString(competencia), Competencia.JavaScript)
    }

    @Test
    void TransformaStringHtml() {
        String competencia = "Html"
        Assertions.assertEquals(CompetenciaView.transformaString(competencia), Competencia.Html)
    }

    @Test
    void TransformaStringNode() {
        String competencia = "Node"
        Assertions.assertEquals(CompetenciaView.transformaString(competencia), Competencia.Node)
    }

    @Test
    void TransformaStringIncorreta() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            String competencia = "Incorreta"
            CompetenciaView.transformaString(competencia)
        })
    }

}
