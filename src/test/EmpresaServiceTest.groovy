package test

import main.groovy.entity.Empresa
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class EmpresaServiceTest {
    @Test
    public void testAdicionaEmpresa() {
        List<Empresa> empresas = new ArrayList<>()
        empresas.add(new Empresa("main.groovy.entity.Empresa Teste", "empresaTeste@gmail.com", "213-5", "Brasil", "Goiás", "32132-431", "main.groovy.entity.Empresa de teste", [Competencia.SpringFramework, Competencia.Java]))
        Empresa resul = new Empresa("main.groovy.entity.Empresa Teste", "empresaTeste@gmail.com", "213-5", "Brasil", "Goiás", "32132-431", "main.groovy.entity.Empresa de teste", [Competencia.SpringFramework, Competencia.Java])
        assertEquals(empresas.get(0), resul);
    }
}
