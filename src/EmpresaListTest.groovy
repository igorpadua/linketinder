import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class EmpresaListTest {
    @Test
    public void testAdicionaEmpresa() {
        List<Empresa> empresas = new ArrayList<>()
        empresas.add(new Empresa("Empresa Teste", "empresaTeste@gmail.com", "213-5", "Brasil", "Goiás", "32132-431", "Empresa de teste", [Competencia.SpringFramework, Competencia.Java]))
        Empresa resul = new Empresa("Empresa Teste", "empresaTeste@gmail.com", "213-5", "Brasil", "Goiás", "32132-431", "Empresa de teste", [Competencia.SpringFramework, Competencia.Java])
        assertEquals(empresas.get(0), resul);
    }
}
