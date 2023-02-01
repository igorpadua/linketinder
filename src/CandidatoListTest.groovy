import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertEquals;

class CandidatoListTest {
    @Test
    public void testAdicionaCandidato() {
        List<Pessoa> pessoas = new ArrayList<>()
        pessoas.add(new Pessoa("Fernanda Silva", "fernanda@gmail.com", "32645129421", 31, "85324-154", "Programadora Back-end", [Competencia.cplusplus, Competencia.c, Competencia.Java]))
        Pessoa resul = new Pessoa("Fernanda Silva", "fernanda@gmail.com", "32645129421", 31, "85324-154", "Programadora Back-end", [Competencia.cplusplus, Competencia.c, Competencia.Java])
        assertEquals(pessoas.get(0), resul);
    }
}
