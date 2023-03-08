package com.igor.linketinder

import com.igor.linketinder.entity.Empresa
import com.igor.linketinder.service.EmpresaService
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class EmpresaServiceTest {
    @Test
    void testAdicionaEmpresa() {
        List<Empresa> empresas = new ArrayList<>()
        empresas.add(new Empresa("Empresa Teste", "empresaTeste@gmail.com", "50.000.000/0000-00", "Brasil", "00000-00", "32132-431", "1234"))
        Empresa resul = new Empresa("Empresa Teste", "empresaTeste@gmail.com", "50.000.000/0000-00", "Brasil", "00000-00", "32132-431", "1234")
        assertEquals(empresas.get(0), resul)
    }

    @Test
    void cnpjValidoTest() {
        String cnpj = "50.000.000/0000-00"
        Boolean resul = EmpresaService.validaCNPJ(cnpj)
        assertEquals(true, resul)
    }

    @Test
    void cnpjNulo() {
        String cnpj = null
        Boolean resul = EmpresaService.validaCNPJ(cnpj)
        assertEquals(false, resul)
    }

    @Test
    void cnpjInvalidoLetraTest() {
        String cnpj = "50.000.000/0000-0A"
        Boolean resul = EmpresaService.validaCNPJ(cnpj)
        assertEquals(false, resul)
    }

    @Test
    void cnpjPequenoTest() {
        String cnpj = "50.000.000/0000-0"
        Boolean resul = EmpresaService.validaCNPJ(cnpj)
        assertEquals(false, resul)
    }
}
