package com.igor.linketinder.controller

import com.igor.linketinder.dao.CompetenciaVagasDAO
import com.igor.linketinder.dao.EmpresaDAO
import com.igor.linketinder.dao.VagaDAO
import com.igor.linketinder.entity.Vaga
import com.igor.linketinder.service.EmpresaService
import com.igor.linketinder.service.VagaService

class VagaController {
    static void adicionar() {
        // Adicionar uma vaga
        final String cnpj = EmpresaService.pegaCnpj()
        // Pega o ID
        final int id = EmpresaDAO.getIdEmpresa(cnpj)
        Vaga vaga = VagaService.adicionaVaga()
        VagaDAO.inserirVaga(vaga, id)
        // Adiciona as competencias da vaga
        CompetenciaVagasDAO.inserirCompetenciaVaga(vaga, id)
        println("\nAdicionado com sucesso\n")
    }

    static void atualizar() {
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
    }

    static void remover() {
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
    }

    static void listar() {
        // Listar todas as vagas
        VagaService.printVagas(VagaDAO.listarVagas())
    }
}
