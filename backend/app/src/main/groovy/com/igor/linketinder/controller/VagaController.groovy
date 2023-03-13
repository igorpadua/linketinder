package com.igor.linketinder.controller

import com.igor.linketinder.dao.CompetenciaVagasDAO
import com.igor.linketinder.dao.EmpresaDAO
import com.igor.linketinder.dao.VagaDAO
import com.igor.linketinder.model.Vaga
import com.igor.linketinder.fabricaBanco.FabricaBanco
import com.igor.linketinder.fabricaBanco.PostgesFabric
import com.igor.linketinder.view.EmpresaView
import com.igor.linketinder.view.VagaView

class VagaController {

    private static final FabricaBanco fabricaBanco = FabricaBanco.criaInstancia(new PostgesFabric())
    private static final VagaDAO vagaDAO = new VagaDAO(fabricaBanco)
    private static final CompetenciaVagasDAO competenciaVagasDAO = new CompetenciaVagasDAO(fabricaBanco)
    private static final EmpresaDAO empresaDAO = new EmpresaDAO(fabricaBanco)

    static void adicionar() {
        final String cnpj = EmpresaView.pegaCnpj()
        final int idEmpresa = empresaDAO.pegaId(cnpj)
        Vaga vaga = VagaView.adicionaVaga()
        vagaDAO.adicionar(vaga, idEmpresa)
        competenciaVagasDAO.adicionar(vaga, idEmpresa)
        println("\nAdicionado com sucesso\n")
    }

    static void atualizar() {
        final int idVaga = VagaView.pegaID()
        Vaga vaga = vagaDAO.pega(idVaga)
        VagaView.atualizarVaga(vaga)
        vagaDAO.atualiza(vaga, idVaga)
        competenciaVagasDAO.atualizar(vaga, idVaga)
    }

    static void remover() {
        final int idVaga = VagaView.pegaID()
        competenciaVagasDAO.remove(idVaga)
        vagaDAO.remove(idVaga)
        println("\nRemovido com sucesso\n")
    }

    static void listar() {
        VagaView.printVagas(vagaDAO.listaComTodasVagas())
    }
}
