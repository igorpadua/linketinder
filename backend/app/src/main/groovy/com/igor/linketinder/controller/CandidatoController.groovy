package com.igor.linketinder.controller

import com.igor.linketinder.dao.CandidatoDAO
import com.igor.linketinder.dao.CompetenciaCandidatoDAO
import com.igor.linketinder.entity.Candidato
import com.igor.linketinder.service.CandidatoService
import com.igor.linketinder.service.PessoaService

class CandidatoController {
    static void adicionar() {
        Candidato candidato = CandidatoService.newCandidato()
        CandidatoDAO.adiciona(candidato)
        CompetenciaCandidatoDAO.adicionar(candidato)
        println("\nAdicionado com sucesso\n")
    }

    static void atualizar() {
        final String cpf = CandidatoService.pegaCPF()
        Candidato candidato = CandidatoDAO.pega(cpf)
        CandidatoService.atualizarCandidato(candidato)
        CandidatoDAO.atualiza(candidato)
        CompetenciaCandidatoDAO.atualizar(candidato)
        println("\nAtualizado com sucesso\n")
    }

    static void remover() {
        final String cpf = CandidatoService.pegaCPF()
        CompetenciaCandidatoDAO.remove(cpf)
        CandidatoDAO.remove(cpf)
        println("\nRemovido com sucesso\n")
    }

    static void listar() {
        PessoaService.imprimir(CandidatoDAO.listaComTodosCandidatos())
    }
}
