import groovy.transform.Canonical
import groovy.transform.TypeChecked

@TypeChecked
@Canonical
class Empresa {
    String nome
    String email
    String cnpj
    String pais
    String estado
    String cep
    String desc
    ArrayList<Competencia> competencias
}
