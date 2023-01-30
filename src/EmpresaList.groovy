import groovy.transform.TypeChecked

@TypeChecked
class EmpresaList {
    static Empresa newEmpresa(List<Empresa> empresas) {
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
        print("Digite o CEP  do candidato: ")
        String cep = scanner.nextLine()
        print("Digite uma descrição do candidato: ")
        String desc = scanner.nextLine()

        new Empresa(nome, email, cnpj, pais, estado, cep, desc)
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
