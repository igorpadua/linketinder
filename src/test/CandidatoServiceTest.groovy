package test

import groovy.transform.TypeChecked
import main.groovy.entity.Candidato
import main.groovy.entity.Competencia
import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertEquals;

@TypeChecked
class CandidatoServiceTest {
    @Test
    public void testAdicionaCandidato() {
        List<Candidato> pessoas = new ArrayList<>()
        pessoas.add(new Candidato('Joao', 'Silva', new Date(2000-04-23), 'joao@gmail.com', '000.000.000-00', 'Brasil', '00000-00', 'Sou um candidato', '123456', [Competencia.cplusplus, Competencia.Java] as ArrayList<Competencia>))
        Candidato resul = new Candidato('Joao', 'Silva', new Date(2000-04-23), 'joao@gmail.com', '000.000.000-00', 'Brasil', '00000-00', 'Sou um candidato', '123456', [Competencia.cplusplus, Competencia.Java] as ArrayList<Competencia>)
        assertEquals(pessoas.get(0), resul);
    }
}
