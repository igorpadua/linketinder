package com.igor.linketinder.controller

import com.igor.linketinder.dao.CandidatoDAO
import com.igor.linketinder.dao.CompetenciaCandidatoDAO
import com.igor.linketinder.entity.Candidato
import com.igor.linketinder.view.CandidatoView
import com.igor.linketinder.view.PessoaView

class CandidatoController {
    static void adicionar() {
        Candidato candidato = CandidatoView.cria()
        CandidatoDAO.adiciona(candidato)
        CompetenciaCandidatoDAO.adicionar(candidato)
        println("\nAdicionado com sucesso\n")
    }

    static void atualizar() {
        final String cpf = CandidatoView.pegaCPF()
        Candidato candidato = CandidatoDAO.pega(cpf)
        CandidatoView.atualizar(candidato)
        CandidatoDAO.atualiza(candidato)
        CompetenciaCandidatoDAO.atualizar(candidato)
        println("\nAtualizado com sucesso\n")
    }

    static void remover() {
        final String cpf = CandidatoView.pegaCPF()
        CompetenciaCandidatoDAO.remove(cpf)
        CandidatoDAO.remove(cpf)
        println("\nRemovido com sucesso\n")
    }

    static void listar() {
        PessoaView.imprimir(CandidatoDAO.listaComTodosCandidatos())
    }
}
