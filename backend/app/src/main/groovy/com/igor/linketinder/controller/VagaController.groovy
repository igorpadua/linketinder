package com.igor.linketinder.controller

import com.igor.linketinder.dao.CompetenciaVagasDAO
import com.igor.linketinder.dao.EmpresaDAO
import com.igor.linketinder.dao.VagaDAO
import com.igor.linketinder.entity.Vaga
import com.igor.linketinder.view.EmpresaView
import com.igor.linketinder.view.VagaView

class VagaController {
    static void adicionar() {
        // Adicionar uma vaga
        final String cnpj = EmpresaView.pegaCnpj()
        // Pega o ID
        final int idEmpresa = EmpresaDAO.pegaId(cnpj)
        Vaga vaga = VagaView.adicionaVaga()
        VagaDAO.adicionar(vaga, idEmpresa)
        // Adiciona as competencias da vaga
        CompetenciaVagasDAO.adicionar(vaga, idEmpresa)
        println("\nAdicionado com sucesso\n")
    }

    static void atualizar() {
        // Atualizar uma vaga
        final int id = VagaView.pegaID()
        // Pega a vaga do banco de dados
        Vaga vaga = VagaDAO.pega(id)
        // Atualiza a vaga
        VagaView.atualizarVaga(vaga)
        // Atualiza a vaga no banco de dados
        VagaDAO.atualiza(vaga, id)
        // Atualiza as competencias da vaga
        CompetenciaVagasDAO.atualizar(vaga, id)
    }

    static void remover() {
        // Remover uma vaga
        final int id = VagaView.pegaID()
        CompetenciaVagasDAO.remove(id)
        VagaDAO.remove(id)
        println("\nRemovido com sucesso\n")
    }

    static void listar() {
        // Listar todas as vagas
        VagaView.printVagas(VagaDAO.listaComTodasVagas())
    }
}
