package com.igor.linketinder.controller

import com.igor.linketinder.dao.CandidatoDAO
import com.igor.linketinder.dao.CompetenciaCandidatoDAO
import com.igor.linketinder.entity.Candidato
import com.igor.linketinder.service.CandidatoService
import com.igor.linketinder.service.PessoaService

class CandidatoController {
    static void adicionar() {
        Candidato candidato = CandidatoService.newCandidato()
        CandidatoDAO.inserirCandidato(candidato)
        CompetenciaCandidatoDAO.inserirCompetenciaCandidato(candidato)
        println("\nAdicionado com sucesso\n")
    }

    static void atualizar() {
        // Atualizar um candidato
        final String cpf = CandidatoService.pegaCPF()
        // Pega o candidato do banco de dados
        Candidato candidato = CandidatoDAO.getCandidato(cpf)
        // Atualiza o candidato
        CandidatoService.atualizarCandidato(candidato)
        // Atualiza o candidato no banco de dados
        CandidatoDAO.atualizarCandidato(candidato)
        // Atualiza as competencias do candidato
        CompetenciaCandidatoDAO.atualizarCompetenciaCandidato(candidato)
        println("\nAtualizado com sucesso\n")
    }

    static void remover() {
        final String cpf = CandidatoService.pegaCPF()
        CompetenciaCandidatoDAO.removeCompetenciaCandidato(cpf)
        CandidatoDAO.removeCandidato(cpf)
        println("\nRemovido com sucesso\n")
    }

    static void listar() {
        PessoaService.imprimir(CandidatoDAO.listaTodosCandidatos())
    }
}