package com.igor.linketinder.controller

import com.igor.linketinder.dao.CompetenciaVagasDAO
import com.igor.linketinder.dao.EmpresaDAO
import com.igor.linketinder.dao.VagaDAO
import com.igor.linketinder.entity.Vaga
import com.igor.linketinder.view.EmpresaView
import com.igor.linketinder.view.VagaView

class VagaController {
    static void adicionar() {
        final String cnpj = EmpresaView.pegaCnpj()
        final int idEmpresa = EmpresaDAO.pegaId(cnpj)
        Vaga vaga = VagaView.adicionaVaga()
        VagaDAO.adicionar(vaga, idEmpresa)
        CompetenciaVagasDAO.adicionar(vaga, idEmpresa)
        println("\nAdicionado com sucesso\n")
    }

    static void atualizar() {
        final int idVaga = VagaView.pegaID()
        Vaga vaga = VagaDAO.pega(idVaga)
        VagaView.atualizarVaga(vaga)
        VagaDAO.atualiza(vaga, idVaga)
        CompetenciaVagasDAO.atualizar(vaga, idVaga)
    }

    static void remover() {
        final int idVaga = VagaView.pegaID()
        CompetenciaVagasDAO.remove(idVaga)
        VagaDAO.remove(idVaga)
        println("\nRemovido com sucesso\n")
    }

    static void listar() {
        VagaView.printVagas(VagaDAO.listaComTodasVagas())
    }
}
