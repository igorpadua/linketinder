package com.igor.linketinder.controller

import com.igor.linketinder.dao.CandidatoDAO
import com.igor.linketinder.dao.CompetenciaCandidatoDAO
import com.igor.linketinder.model.Candidato
import com.igor.linketinder.fabricaBanco.FabricaBanco
import com.igor.linketinder.fabricaBanco.PostgesFabric
import com.igor.linketinder.util.Validacoes
import groovy.transform.TypeChecked

@TypeChecked
class CandidatoController {

    private static final FabricaBanco fabricaBanco = FabricaBanco.criaInstancia(new PostgesFabric())
    private static final CandidatoDAO candidatoDAO = new CandidatoDAO(fabricaBanco)
    private static final CompetenciaCandidatoDAO competenciaCandidatoDAO = new CompetenciaCandidatoDAO(fabricaBanco)

    static void salvarNoBanco(Candidato candidato) {
        if (!Validacoes.validaCPF(candidato.cpf)) throw new IllegalArgumentException("CPF inválido")
        if (!Validacoes.validaCEP(candidato.cep)) throw new IllegalArgumentException("CEP inválido")
        candidatoDAO.salvar(candidato)
        competenciaCandidatoDAO.salvar(candidato)
    }

    static String pegaCpf(String cpf) {
        return candidatoDAO.pegar(cpf)
    }

    static void atualizarNoBanco(Candidato candidato) {
        candidatoDAO.atualiza(candidato)
        competenciaCandidatoDAO.atualizar(candidato)
    }

    static void remover(String cpf) {
        competenciaCandidatoDAO.remove(cpf)
        candidatoDAO.remove(cpf)
    }

    static List<Candidato> listar() {
        return candidatoDAO.listaComTodosCandidatos()
    }
}
