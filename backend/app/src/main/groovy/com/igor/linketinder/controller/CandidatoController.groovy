package com.igor.linketinder.controller

import com.igor.linketinder.dao.CandidatoDAO
import com.igor.linketinder.dao.CompetenciaCandidatoDAO
import com.igor.linketinder.model.Candidato
import com.igor.linketinder.fabricaBanco.FabricaBanco
import com.igor.linketinder.fabricaBanco.PostgesFabric
import com.igor.linketinder.view.CandidatoView
import com.igor.linketinder.view.PessoaView

class CandidatoController {

    private static final FabricaBanco fabricaBanco = FabricaBanco.criaInstancia(new PostgesFabric())
    private static final CandidatoDAO candidatoDAO = new CandidatoDAO(fabricaBanco)
    private static final CompetenciaCandidatoDAO competenciaCandidatoDAO = new CompetenciaCandidatoDAO(fabricaBanco)

    static void adicionar() {
        Candidato candidato = CandidatoView.cria()
        candidatoDAO.salvar(candidato)
        competenciaCandidatoDAO.salvar(candidato)
        println("\nAdicionado com sucesso\n")
    }

    static void atualizar() {
        final String cpf = CandidatoView.pegaCPF()
        Candidato candidato = candidatoDAO.pegar(cpf)
        CandidatoView.atualizar(candidato)
        candidatoDAO.atualiza(candidato)
        competenciaCandidatoDAO.atualizar(candidato)
        println("\nAtualizado com sucesso\n")
    }

    static void remover() {
        final String cpf = CandidatoView.pegaCPF()
        competenciaCandidatoDAO.remove(cpf)
        candidatoDAO.remove(cpf)
        println("\nRemovido com sucesso\n")
    }

    static void listar() {
        PessoaView.imprimir(candidatoDAO.listaComTodosCandidatos())
    }
}
