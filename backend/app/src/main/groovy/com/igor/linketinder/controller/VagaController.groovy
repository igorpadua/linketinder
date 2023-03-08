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
        final int idEmpresa = EmpresaDAO.pegaId(cnpj)
        Vaga vaga = VagaService.adicionaVaga()
        VagaDAO.adicionar(vaga, idEmpresa)
        // Adiciona as competencias da vaga
        CompetenciaVagasDAO.adicionar(vaga, idEmpresa)
        println("\nAdicionado com sucesso\n")
    }

    static void atualizar() {
        // Atualizar uma vaga
        final int id = VagaService.pegaID()
        // Pega a vaga do banco de dados
        Vaga vaga = VagaDAO.pega(id)
        println(vaga)
        // Atualiza a vaga
        VagaService.atualizarVaga(vaga)
        // Atualiza a vaga no banco de dados
        VagaDAO.atualiza(vaga, id)
        // Atualiza as competencias da vaga
        CompetenciaVagasDAO.atualizar(vaga, id)
    }

    static void remover() {
        // Remover uma vaga
        final int id = VagaService.pegaID()
        CompetenciaVagasDAO.remove(id)
        VagaDAO.remove(id)
        println("\nRemovido com sucesso\n")
    }

    static void listar() {
        // Listar todas as vagas
        VagaService.printVagas(VagaDAO.listaComTodasVagas())
    }
}
