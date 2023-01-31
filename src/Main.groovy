static void main(String[] args) {
  List<Pessoa> candidatos = new ArrayList<>()
  List<Empresa> empresas = new ArrayList<>()

  candidatos.add(new Pessoa("João Ferreira", "joao@gmail.com", "56395129421", 23, "23148-320", "Programador Back-end", [Competencia.c, Competencia.SpringFramework]))
  candidatos.add(new Pessoa("Marcela Alves", "marcela@gmail.com", "985615129421", 26, "59234-580", "Programadora front-end", [Competencia.Html, Competencia.JavaScript]))
  candidatos.add(new Pessoa("Guilherme Nunes", "guilhereme@gmail.com", "850915129421", 19, "95832-580", "Programadora full-stack", [Competencia.Html, Competencia.JavaScript, Competencia.Java]))
  candidatos.add(new Pessoa("Ana Beatriz", "ana@gmail.com", "23195129421", 30, "98532-320", "Programadora Back-end", [Competencia.c, Competencia.SpringFramework, Competencia.Java, Competencia.cplusplus]))
  candidatos.add(new Pessoa("Danilo Mendes", "danilo@gmail.com", "982395129421", 34, "89532-320", "Programadora Front-end", [Competencia.Html, Competencia.JavaScript, Competencia.Node]))

  empresas.add(new Empresa("Empresa 1", "empresa1@gmail.com", "213-5", "Brasil", "Goiás", "38432-233", "Empresa de imoveis", [Competencia.Node, Competencia.Java]))
  empresas.add(new Empresa("Empresa 2", "empresa2@gmail.com", "213-5", "Brasil", "São Paulo", "95843-233", "Empresa de vendas de salgados", [Competencia.Html, Competencia.JavaScript]))
  empresas.add(new Empresa("Empresa 3", "empresa3@gmail.com", "213-5", "Brasil", "Rio Grande do Sul", "1592-233", "Empresa de software", [Competencia.SpringFramework, Competencia.Java]))
  empresas.add(new Empresa("Empresa 4", "empresa4@gmail.com", "213-5", "Brasil", "São Paulo", "34910-233", "Banco", [Competencia.Node, Competencia.SpringFramework, Competencia.cplusplus]))
  empresas.add(new Empresa("Empresa 5", "empresa5@gmail.com", "213-5", "Brasil", "São Paulo", "59134-233", "Banco", [Competencia.Python, Competencia.Angular, Competencia.Html]))

  menu()

  Boolean end = true
  Scanner scanner = new Scanner(System.in)

  int opc = 0

  while (end) {
    opc = scanner.nextInt()
    switch (opc) {
      case 1 :
        candidatos.add(CandidatoList.newCandidato(candidatos))
        println("\nAdicionado com sucesso\n")
        // scanner = new Scanner()
        break
      case 2:
        empresas.add(EmpresaList.newEmpresa(empresas))
        println("\nAdiciona com sucesso\n")
        break
      case 3:
        CandidatoList.printCandidatos(candidatos)
        break
      case 4:
        EmpresaList.printEmpresas(empresas)
        break
      case 5:
        end = false
        println("Saiu com sucesso")
        break
      default:
        menu()
        println("Opção incorreta")
    }
  }

}

static void menu() {
  println("1 - Adicionar um novo candidato")
  println("2 - Adicionar uma nova empresa")
  println("3 - Listar candidatos")
  println("4 - Listar empresas")
  println("5 - Sair")
}