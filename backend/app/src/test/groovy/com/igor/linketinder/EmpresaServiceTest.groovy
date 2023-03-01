package test.groovy.com.igor.linketinder

import main.groovy.com.igor.linketinder.entity.Empresa
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class EmpresaServiceTest {
    @Test
    public void testAdicionaEmpresa() {
        List<Empresa> empresas = new ArrayList<>()
        empresas.add(new Empresa("Empresa Teste", "empresaTeste@gmail.com", "50.000.000/0000-00", "Brasil", "00000-00", "32132-431", "1234"))
        Empresa resul = new Empresa("Empresa Teste", "empresaTeste@gmail.com", "50.000.000/0000-00", "Brasil", "00000-00", "32132-431", "1234")
        assertEquals(empresas.get(0), resul);
    }
}
