package main.groovy.service

import main.groovy.entity.Competencia
import main.groovy.entity.Empresa
import groovy.transform.TypeChecked

@TypeChecked
class EmpresaService {
    static Empresa newEmpresa() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o nome da nova empresa: ")
        String nome = scanner.nextLine()
        print("Digite o email da empresa: ")
        String email = scanner.nextLine()
        print("Digite o CNPJ da empresa: ")
        String cnpj = scanner.nextLine()
        print("Digite o pais da empresa: ")
        String pais = scanner.nextLine()
        print("Digite o estado da empresa: ")
        String estado = scanner.nextLine()
        print("Digite o CEP  da empresa: ")
        String cep = scanner.nextLine()
        print("Digite uma descrição da empresa: ")
        String desc = scanner.nextLine()

        ArrayList<Competencia> aux = new ArrayList<>()
        aux = CompetenciaService.choiseCompetencia()

        new Empresa(nome, email, cnpj, pais, estado, cep, desc, aux)
    }

    static void printEmpresas(List<Empresa> empresas) {

        if (empresas.isEmpty()) {
            println("Não existe candidatos")
        }

        for (empresa in empresas) {
            println(empresa)
        }
    }
}
