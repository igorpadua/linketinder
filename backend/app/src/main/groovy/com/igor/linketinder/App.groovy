package com.igor.linketinder

import com.igor.linketinder.controller.CandidatoController
import com.igor.linketinder.controller.EmpresaController
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
import com.igor.linketinder.service.PessoaService
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
                    CandidatoController.adicionar()
                    break
                case 2:
                    EmpresaController.adicionar()
                    break
                case 3:
                    // Adicionar uma vaga
                    final String cnpj = EmpresaService.pegaCnpj()
                    // Pega o ID
                    final int id = EmpresaDAO.getIdEmpresa(cnpj)
                    Vaga vaga = VagaService.adicionaVaga()
                    VagaDAO.inserirVaga(vaga, id)
                    // Adiciona as competencias da vaga
                    CompetenciaVagasDAO.inserirCompetenciaVaga(vaga, id)
                    println("\nAdicionado com sucesso\n")
                    break
                case 4:
                    CandidatoController.atualizar()
                    break
                case 5:
                    EmpresaController.atualizar()
                    break
                case 6:
                    // Atualizar uma vaga
                    final String nome = VagaService.pegaNomeVaga()
                    final String cnpj = EmpresaService.pegaCnpj()
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
                    CandidatoController.remover()
                    break
                case 8:
                    EmpresaController.remover()
                    break
                case 9:
                    // Remover uma vaga
                    final String nome = VagaService.pegaNomeVaga()
                    final String cnpj = EmpresaService.pegaCnpj()
                    // Pega o ID Empresas
                    final int id = EmpresaDAO.getIdEmpresa(cnpj)
                    // Pega o ID Vagas
                    final int idVaga = VagaDAO.getIdVaga(nome, id)
                    CompetenciaVagasDAO.removeCompetenciaVaga(idVaga)
                    VagaDAO.removeVaga(nome, id)
                    println("\nRemovido com sucesso\n")
                    break
                case 10:
                    CandidatoController.listar()
                    break
                case 11:
                    EmpresaController.listar()
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
