import groovy.transform.TypeChecked

@TypeChecked
class CandidatoList {
    static Pessoa newCandidato(List<Pessoa> pessoas) {
        Scanner scanner = new Scanner(System.in)
        print("Digite o nome do novo candidato: ")
        String nome = scanner.nextLine()
        print("Digite o email do candidato: ")
        String email = scanner.nextLine()
        print("Digite o CPF do candidato: ")
        String cpf = scanner.nextLine()
        print("Digite a idade do candidato: ")
        int idade = scanner.nextInt()
        scanner = new Scanner(System.in)
        print("Digite o CEP  do candidato: ")
        String cep = scanner.nextLine()
        print("Digite uma descrição do candidato: ")
        String desc = scanner.nextLine()

        ArrayList<Competencia> aux = new ArrayList<>()
        aux = CompetenciaAbstract.choiseCompetencia()

        new Pessoa(nome, email, cpf, idade, cep, desc, aux)
    }

    static void printCandidatos(List<Pessoa> pessoas) {

        if (pessoas.isEmpty()) {
            println("Não existe candidatos")
        }

        for (candidatos in pessoas) {
            println(candidatos)
        }
    }

}
