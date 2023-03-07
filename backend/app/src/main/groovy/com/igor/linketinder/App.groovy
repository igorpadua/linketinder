package com.igor.linketinder

import com.igor.linketinder.dao.CandidatoDAO
import com.igor.linketinder.dao.CompetenciaCandidatoDAO
import com.igor.linketinder.dao.CompetenciaVagasDAO
import com.igor.linketinder.dao.EmpresaDAO
import com.igor.linketinder.dao.VagaDAO
import com.igor.linketinder.entity.Candidato
import com.igor.linketinder.entity.Empresa
import com.igor.linketinder.entity.Vaga
import com.igor.linketinder.service.CandidatoService
import com.igor.linketinder.service.EmpresaService
import com.igor.linketinder.service.VagaService

class App {
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

    static void main(String[] args) {
        Boolean end = true
        Scanner scanner = new Scanner(System.in)

        while (end) {
            menu()
            int opc = scanner.nextInt()
            switch (opc) {
                case 1 :
                    // Adicionar um candidato
                    Candidato candidato = CandidatoService.newCandidato()
                    CandidatoDAO.inserirCandidato(candidato)
                    // Adiciona as competencias do candidato
                    CompetenciaCandidatoDAO.inserirCompetenciaCandidato(candidato)
                    println("\nAdicionado com sucesso\n")
                    break
                case 2:
                    // Adicionar uma empresa
                    Empresa empresa = EmpresaService.newEmpresa()
                    EmpresaDAO.inserirEmpresa(empresa) println("\nAdiciona com sucesso\n")
                    break
                case 3:
                    // Adicionar uma vaga
                    final String cnpj = EmpresaService.pegaCnpjEmpresa()
                    // Pega o ID
                    final int id = EmpresaDAO.getIdEmpresa(cnpj)
                    Vaga vaga = VagaService.adicionaVaga()
                    VagaDAO.inserirVaga(vaga, id)
                    // Adiciona as competencias da vaga
                    CompetenciaVagasDAO.inserirCompetenciaVaga(vaga, id)
                    println("\nAdicionado com sucesso\n")
                    break
                case 4:
                    // Atualizar um candidato
                    final String cpf = CandidatoService.pegaCpfCandidado()
                    // Pega o candidato do banco de dados
                    Candidato candidato = CandidatoDAO.getCandidato(cpf)
                    // Atualiza o candidato
                    CandidatoService.atualizarCandidato(candidato)
                    // Atualiza o candidato no banco de dados
                    CandidatoDAO.atualizarCandidato(candidato)
                    // Atualiza as competencias do candidato
                    CompetenciaCandidatoDAO.atualizarCompetenciaCandidato(candidato)
                    println("\nAtualizado com sucesso\n")
                    break
                case 5:
                    // Atualizar uma empresa
                    final String cnpj = EmpresaService.pegaCnpjEmpresa()
                    // Pega a empresa do banco de dados
                    Empresa empresa = EmpresaDAO.getEmpresa(cnpj)
                    // Atualiza a empresa
                    EmpresaService.atualizarEmpresa(empresa)
                    // Atualiza a empresa no banco de dados
                    EmpresaDAO.atualizarEmpresa(empresa)
                    println("\nAtualizado com sucesso\n")
                    break
                case 6:
                    // Atualizar uma vaga
                    final String nome = VagaService.pegaNomeVaga()
                    final String cnpj = EmpresaService.pegaCnpjEmpresa()
                    // Pega o ID Empresa
                    final int id = EmpresaDAO.getIdEmpresa(cnpj)
                    // Pega a vaga do banco de dados
                    Vaga vaga = VagaDAO.getVaga(nome, id)
                    // Atualiza a vaga
                    VagaService.atualizarVaga(vaga)
                    // Atualiza a vaga no banco de dados
                    VagaDAO.atualizarVaga(vaga, nome, id)
                    // Atualiza as competencias da vaga
                    CompetenciaVagasDAO.atualizarCompetenciaVaga(vaga, id)
                    break
                case 7:
                    // Remover um candidato
                    final String cpf = CandidatoService.pegaCpfCandidado()
                    CompetenciaCandidatoDAO.removeCompetenciaCandidato(cpf)
                    CandidatoDAO.removeCandidato(cpf)
                    println("\nRemovido com sucesso\n")
                    break
                case 8:
                    // Remover uma empresa
                    final String cnpj = EmpresaService.pegaCnpjEmpresa()
                    EmpresaDAO.removeEmpresa(cnpj)
                    println("\nRemovido com sucesso\n")
                    break
                case 9:
                    // Remover uma vaga
                    final String nome = VagaService.pegaNomeVaga()
                    final String cnpj = EmpresaService.pegaCnpjEmpresa()
                    // Pega o ID Empresas
                    final int id = EmpresaDAO.getIdEmpresa(cnpj)
                    // Pega o ID Vagas
                    final int idVaga = VagaDAO.getIdVaga(nome, id)
                    CompetenciaVagasDAO.removeCompetenciaVaga(idVaga)
                    VagaDAO.removeVaga(nome, id)
                    println("\nRemovido com sucesso\n")
                    break
                case 10:
                    // Listar candidatos
                    CandidatoService.printCandidatos(CandidatoDAO.listaTodosCandidatos())
                    break
                case 11:
                    // Listar empresas
                    EmpresaService.printEmpresas(EmpresaDAO.listarEmpresas())
                    break
                case 12:
                    // Listar vagas
                    VagaService.printVagas(VagaDAO.listarVagas())
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
}
