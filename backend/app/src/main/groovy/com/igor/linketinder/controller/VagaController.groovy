package com.igor.linketinder.controller

import com.igor.linketinder.dao.CompetenciaVagasDAO
import com.igor.linketinder.dao.VagaDAO
import com.igor.linketinder.model.Vaga
import com.igor.linketinder.dao.fabricaBanco.FabricaBanco
import com.igor.linketinder.dao.fabricaBanco.PostgesFabric
import com.igor.linketinder.view.VagaView
import groovy.transform.TypeChecked

@TypeChecked
class VagaController {

    private static final FabricaBanco fabricaBanco = FabricaBanco.criaInstancia(new PostgesFabric())
    private static final VagaDAO vagaDAO = new VagaDAO(fabricaBanco)
    private static final CompetenciaVagasDAO competenciaVagasDAO = new CompetenciaVagasDAO(fabricaBanco)

    static void adicionar() {
        Vaga vaga = VagaView.cadastro()
        final int id_empresa = EmpresaController.pegaId(vaga.empresa.cnpj)
        salvarNoBanco(vaga, id_empresa)
        VagaView.adicionadoComSucesso()
    }

    static void atualizar() {
        int id_vaga = VagaView.pegaID()
        final Vaga vaga = pega(id_vaga)
        if (vaga == null) throw new IllegalArgumentException("Vaga não encontrada")
        VagaView.atualiza(vaga)
        atualizarNoBanco(vaga)
        VagaView.atualizadoComSucesso()
    }

    static void remover() {
        int id_vaga = VagaView.pegaID()
        final Vaga vaga = pega(id_vaga)
        if (vaga == null) throw new IllegalArgumentException("Vaga não encontrada")
        removeDoBanco(id_vaga)
        VagaView.removidoComSucesso()
    }

    static Vaga pega(int idVaga) {
        return vagaDAO.pega(idVaga)
    }

    static void listar() {
        List<Vaga> vagas = pegaVagas()
        VagaView.lista(vagas)
    }

    static void salvarNoBanco(Vaga vaga, int idEmpresa) {
        vagaDAO.salvar(vaga, idEmpresa)
        competenciaVagasDAO.salvar(vaga, idEmpresa)
    }

    static void atualizarNoBanco(Vaga vaga) {
        vagaDAO.atualiza(vaga)
        competenciaVagasDAO.atualizar(vaga)
    }

    static void removeDoBanco(int idVaga) {
        vagaDAO.remove(idVaga)
    }

    static List<Vaga> pegaVagas() {
        return vagaDAO.listaComTodasVagas()
    }
}
