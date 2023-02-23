package main.groovy

import main.groovy.entity.Candidato
import main.groovy.entity.Competencia
import main.groovy.entity.Empresa
import groovy.transform.TypeChecked
import main.groovy.service.EmpresaService
import main.groovy.service.CandidatoService

@TypeChecked
static void main(String[] args) {
  List<Candidato> candidatos = new ArrayList<>()
  List<Empresa> empresas = new ArrayList<>()

  candidatos.add(new Candidato("João Ferreira", "joao@gmail.com", "56395129421", 23, "23148-320", "Programador Back-end", [Competencia.c, Competencia.SpringFramework] as ArrayList<Competencia>))
  candidatos.add(new Candidato("Marcela Alves", "marcela@gmail.com", "985615129421", 26, "59234-580", "Programadora front-end", [Competencia.Html, Competencia.JavaScript] as ArrayList<Competencia>))
  candidatos.add(new Candidato("Guilherme Nunes", "guilhereme@gmail.com", "850915129421", 19, "95832-580", "Programadora full-stack", [Competencia.Html, Competencia.JavaScript, Competencia.Java] as ArrayList<Competencia>))
  candidatos.add(new Candidato("Ana Beatriz", "ana@gmail.com", "23195129421", 30, "98532-320", "Programadora Back-end", [Competencia.c, Competencia.SpringFramework, Competencia.Java, Competencia.cplusplus] as ArrayList<Competencia>))
  candidatos.add(new Candidato("Danilo Mendes", "danilo@gmail.com", "982395129421", 34, "89532-320", "Programadora Front-end", [Competencia.Html, Competencia.JavaScript, Competencia.Node] as ArrayList<Competencia>))

  empresas.add(new Empresa("main.groovy.entity.Empresa 1", "empresa1@gmail.com", "213-5", "Brasil", "Goiás", "38432-233", "main.groovy.entity.Empresa de imoveis", [Competencia.Node, Competencia.Java] as ArrayList<Competencia>))
  empresas.add(new Empresa("main.groovy.entity.Empresa 2", "empresa2@gmail.com", "213-5", "Brasil", "São Paulo", "95843-233", "main.groovy.entity.Empresa de vendas de salgados", [Competencia.Html, Competencia.JavaScript] as ArrayList<Competencia>))
  empresas.add(new Empresa("main.groovy.entity.Empresa 3", "empresa3@gmail.com", "213-5", "Brasil", "Rio Grande do Sul", "1592-233", "main.groovy.entity.Empresa de software", [Competencia.SpringFramework, Competencia.Java] as ArrayList<Competencia>))
  empresas.add(new Empresa("main.groovy.entity.Empresa 4", "empresa4@gmail.com", "213-5", "Brasil", "São Paulo", "34910-233", "Banco", [Competencia.Node, Competencia.SpringFramework, Competencia.cplusplus] as ArrayList<Competencia>))
  empresas.add(new Empresa("main.groovy.entity.Empresa 5", "empresa5@gmail.com", "213-5", "Brasil", "São Paulo", "59134-233", "Banco", [Competencia.Python, Competencia.Angular, Competencia.Html] as ArrayList<Competencia>))

  menu()

  Boolean end = true
  Scanner scanner = new Scanner(System.in)

  int opc = 0

  while (end) {
    opc = scanner.nextInt()
    switch (opc) {
      case 1 :
        candidatos.add(CandidatoService.newCandidato())
        println("\nAdicionado com sucesso\n")
        // scanner = new Scanner()
        break
      case 2:
        empresas.add(EmpresaService.newEmpresa())
        println("\nAdiciona com sucesso\n")
        break
      case 3:
        CandidatoService.printCandidatos(candidatos)
        break
      case 4:
        EmpresaService.printEmpresas(empresas)
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