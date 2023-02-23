package test

import main.groovy.entity.Candidato
import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertEquals;

class CandidatoServiceTest {
    @Test
    public void testAdicionaCandidato() {
        List<Candidato> pessoas = new ArrayList<>()
        pessoas.add(new Candidato("Fernanda Silva", "fernanda@gmail.com", "32645129421", 31, "85324-154", "Programadora Back-end", [Competencia.cplusplus, Competencia.c, Competencia.Java]))
        Candidato resul = new Candidato("Fernanda Silva", "fernanda@gmail.com", "32645129421", 31, "85324-154", "Programadora Back-end", [Competencia.cplusplus, Competencia.c, Competencia.Java])
        assertEquals(pessoas.get(0), resul);
    }
}
