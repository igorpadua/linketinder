package main.groovy

import main.groovy.dto.CandidatoDTO
import main.groovy.dto.CompetenciaCandidatoDTO
import main.groovy.dto.CompetenciaVagasDTO
import main.groovy.dto.EmpresaDTO
import main.groovy.dto.VagaDTO
import main.groovy.entity.Candidato
import main.groovy.entity.Empresa
import groovy.transform.TypeChecked
import main.groovy.entity.Vaga
import main.groovy.service.EmpresaService
import main.groovy.service.CandidatoService
import main.groovy.service.VagaService

@TypeChecked
static void main(String[] args) {
  List<Vaga> vagas = []
  Boolean end = true
  Scanner scanner = new Scanner(System.in)

  while (end) {
    menu()
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
        // Adicionar uma vaga
        final String cnpj = EmpresaService.pegaCnpjEmpresa()
        // Pega o ID
        final int id = EmpresaDTO.getIdEmpresa(cnpj)
        Vaga vaga = VagaService.adicionaVaga()
        VagaDTO.inserirVaga(vaga, id)
        // Adiciona as competencias da vaga
        CompetenciaVagasDTO.inserirCompetenciaVaga(vaga, id)
        println("\nAdicionado com sucesso\n")
        break
      case 4:
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
      case 5:
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
      case 6:
        // Atualizar uma vaga
        final String nome = VagaService.pegaNomeVaga()
        final String cnpj = EmpresaService.pegaCnpjEmpresa()
        // Pega o ID
        final int id = EmpresaDTO.getIdEmpresa(cnpj)
        // Pega a vaga do banco de dados
        Vaga vaga = VagaDTO.getVaga(nome, id)
        // Atualiza a vaga
        VagaService.atualizarVaga(vaga)
        // Atualiza a vaga no banco de dados
        VagaDTO.atualizarVaga(vaga, nome, id)
        break
      case 7:
        // Remover um candidato
        final String cpf = CandidatoService.pegaCpfCandidado()
        CompetenciaCandidatoDTO.removeCompetenciaCandidato(cpf)
        CandidatoDTO.removeCandidato(cpf)
        println("\nRemovido com sucesso\n")
        break
      case 8:
        // Remover uma empresa
        final String cnpj = EmpresaService.pegaCnpjEmpresa()
        EmpresaDTO.removeEmpresa(cnpj)
        println("\nRemovido com sucesso\n")
        break
      case 9:
        // Remover uma vaga
        final String nome = VagaService.pegaNomeVaga()
        final String cnpj = EmpresaService.pegaCnpjEmpresa()
        // Pega o ID Empresas
        final int id = EmpresaDTO.getIdEmpresa(cnpj)
        // Pega o ID Vagas
        final int idVaga = VagaDTO.getIdVaga(nome, id)
        CompetenciaVagasDTO.removeCompetenciaVaga(idVaga)
        VagaDTO.removeVaga(nome, id)
        println("\nRemovido com sucesso\n")
        break
      case 10:
        // Listar candidatos
        CandidatoService.printCandidatos(CandidatoDTO.listaTodosCandidatos())
        break
      case 11:
        // Listar empresas
         EmpresaService.printEmpresas(EmpresaDTO.listarEmpresas())
        break
      case 12:
        // Listar vagas
        VagaService.printVagas(VagaDTO.listarVagas())
        break
      case 13:
        end = false
        println("Saiu com sucesso")
        break
      default:
        println("Opção incorreta")
    }
  }

}

static private void menu() {
  println("1 - Adicionar um novo candidato")
  println("2 - Adicionar uma nova empresa")
  println("3 - Adicionar uma nova vaga")
  println("4 - Atualizar um candidato")
  println("5 - Atualizar uma empresa")
  println("6 - Atualizar uma vaga")
  println("7 - Remover um candidato")
  println("8 - Remover uma empresa")
  println("9 - Remover uma vaga")
  println("10 - Listar candidatos")
  println("11 - Listar empresas")
  println("12 - Listar vagas")
  println("13 - Sair")
}