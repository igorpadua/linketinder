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
    void listaVaziaTest() {
        List<Empresa> empresas = new ArrayList<>()
        EmpresaService.imprimir(empresas)
        assertEquals(0, empresas.size())
    }
}
