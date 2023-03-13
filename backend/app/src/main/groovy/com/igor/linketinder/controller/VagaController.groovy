package com.igor.linketinder.controller

import com.igor.linketinder.dao.CompetenciaVagasDAO
import com.igor.linketinder.dao.VagaDAO
import com.igor.linketinder.model.Vaga
import com.igor.linketinder.fabricaBanco.FabricaBanco
import com.igor.linketinder.fabricaBanco.PostgesFabric
import groovy.transform.TypeChecked

@TypeChecked
class VagaController {

    private static final FabricaBanco fabricaBanco = FabricaBanco.criaInstancia(new PostgesFabric())
    private static final VagaDAO vagaDAO = new VagaDAO(fabricaBanco)
    private static final CompetenciaVagasDAO competenciaVagasDAO = new CompetenciaVagasDAO(fabricaBanco)

    static void salvarNoBanco(Vaga vaga, int idEmpresa) {
        vagaDAO.salvar(vaga, idEmpresa)
        competenciaVagasDAO.salvar(vaga, idEmpresa)
    }

    static void atualizarNoBanco(Vaga vaga, int idVaga) {
        vagaDAO.atualiza(vaga, idVaga)
        competenciaVagasDAO.atualizar(vaga, idVaga)
    }

    static void remover(int idVaga) {
        vagaDAO.remove(idVaga)
    }

    static List<Vaga> listar() {
        return vagaDAO.listaComTodasVagas()
    }
}
