package main.groovy

import main.groovy.dto.CandidatoDTO
import main.groovy.dto.CompetenciaCandidatoDTO
import main.groovy.dto.EmpresaDTO
import main.groovy.entity.Candidato
import main.groovy.entity.Empresa
import groovy.transform.TypeChecked
import main.groovy.service.EmpresaService
import main.groovy.service.CandidatoService

@TypeChecked
static void main(String[] args) {
  menu()

  Boolean end = true
  Scanner scanner = new Scanner(System.in)

  while (end) {
    int opc = scanner.nextInt()
    switch (opc) {
      case 1 :
        // Adicionar um candidato
        Candidato candidato = CandidatoService.newCandidato()
        CandidatoDTO.inserirCandidato(candidato)
        // Adiciona as competencias do candidato
        CompetenciaCandidatoDTO.inserirCompetenciaCandidato(candidato)
        println("\nAdicionado com sucesso\n")
        break
      case 2:
        // Adicionar uma empresa
        Empresa empresa = EmpresaService.newEmpresa()
        EmpresaDTO.inserirEmpresa(empresa)
        println("\nAdiciona com sucesso\n")
        break
      case 3:
        // Atualizar um candidato
        final String cpf = CandidatoService.pegaCpfCandidado()
        // Pega o candidato do banco de dados
        Candidato candidato = CandidatoDTO.getCandidato(cpf)
        // Atualiza o candidato
        CandidatoService.atualizarCandidato(candidato)
        // Atualiza o candidato no banco de dados
        CandidatoDTO.atualizarCandidato(candidato)
        // Atualiza as competencias do candidato
        CompetenciaCandidatoDTO.atualizarCompetenciaCandidato(candidato)
        println("\nAtualizado com sucesso\n")
        break
      case 4:
        // Atualizar uma empresa
        final String cnpj = EmpresaService.pegaCnpjEmpresa()
        // Pega a empresa do banco de dados
        Empresa empresa = EmpresaDTO.getEmpresa(cnpj)
        // Atualiza a empresa
        EmpresaService.atualizarEmpresa(empresa)
        // Atualiza a empresa no banco de dados
        EmpresaDTO.atualizarEmpresa(empresa)
        println("\nAtualizado com sucesso\n")
        break
      case 5:
        // Remover um candidato
        final String cpf = CandidatoService.pegaCpfCandidado()
        CompetenciaCandidatoDTO.removeCompetenciaCandidato(cpf)
        CandidatoDTO.removeCandidato(cpf)
        println("\nRemovido com sucesso\n")
        break
      case 6:
        // Remover uma empresa
        final String cnpj = EmpresaService.pegaCnpjEmpresa()
        EmpresaDTO.removeEmpresa(cnpj)
        println("\nRemovido com sucesso\n")
        break
      case 7:
        // Listar candidatos
        CandidatoService.printCandidatos(CandidatoDTO.listaTodosCandidatos())
        break
      case 8:
        // Listar empresas
         EmpresaService.printEmpresas(EmpresaDTO.listarEmpresas())
        break
      case 9:
        end = false
        println("Saiu com sucesso")
        break
      default:
        menu()
        println("Opção incorreta")
    }
  }

}

static private void menu() {
  println("1 - Adicionar um novo candidato")
  println("2 - Adicionar uma nova empresa")
  println("3 - Atualizar um candidato")
  println("4 - Atualizar uma empresa")
  println("5 - Remover um candidato")
  println("6 - Remover uma empresa")
  println("7 - Listar candidatos")
  println("8 - Listar empresas")
  println("9 - Sair")
}