package com.igor.linketinder.controller

import com.igor.linketinder.dao.CandidatoDAO
import com.igor.linketinder.dao.CompetenciaCandidatoDAO
import com.igor.linketinder.model.Candidato
import com.igor.linketinder.dao.fabricaBanco.FabricaBanco
import com.igor.linketinder.dao.fabricaBanco.PostgesFabric
import com.igor.linketinder.model.Pessoa
import com.igor.linketinder.util.Validacoes
import com.igor.linketinder.view.CandidatoView
import com.igor.linketinder.view.PessoaView
import groovy.transform.TypeChecked

@TypeChecked
class CandidatoController {

    private static final FabricaBanco fabricaBanco = FabricaBanco.criaInstancia(new PostgesFabric())
    private static final CandidatoDAO candidatoDAO = new CandidatoDAO(fabricaBanco)
    private static final CompetenciaCandidatoDAO competenciaCandidatoDAO = new CompetenciaCandidatoDAO(fabricaBanco)

    static void adicionar() {
        Candidato candidato = CandidatoView.cadastro()
        salvarNoBanco(candidato)
        CandidatoView.adicionadoComSucesso()
    }

    static void atualizar() {
        final String CPF = CandidatoView.pegaCpf()
        if (!Validacoes.validaCPF(CPF)) throw new IllegalArgumentException("CPF inválido")
        final Candidato candidato = pega(CPF)
        if (candidato == null) throw new IllegalArgumentException("Candidato não encontrado")
        CandidatoView.atualiza(candidato)
        atualizarNoBanco(candidato)
        CandidatoView.atualizadoComSucesso()
    }

    static void remover() {
        final String CPF = CandidatoView.pegaCpf()
        if (!Validacoes.validaCPF(CPF)) throw new IllegalArgumentException("CPF inválido")
        removeNoBanco(CPF)
        CandidatoView.removidoComSucesso()
    }

    static void listar() {
        List<Pessoa> candidatoLista = pegaCandidos() as List<Pessoa>
        PessoaView.lista(candidatoLista)
    }

    static void salvarNoBanco(Candidato candidato) {
        if (!Validacoes.validaCPF(candidato.cpf)) throw new IllegalArgumentException("CPF inválido")
        if (!Validacoes.validaCEP(candidato.cep)) throw new IllegalArgumentException("CEP inválido")
        candidatoDAO.salvar(candidato)
        competenciaCandidatoDAO.salvar(candidato)
    }


    static Candidato pega(String cpf) {
        return candidatoDAO.pegar(cpf)
    }

    static void atualizarNoBanco(Candidato candidato) {
        candidatoDAO.atualiza(candidato)
        competenciaCandidatoDAO.atualizar(candidato)
    }

    static void removeNoBanco(String cpf) {
        competenciaCandidatoDAO.remove(cpf)
        candidatoDAO.remove(cpf)
    }

    static List<Candidato> pegaCandidos() {
        return candidatoDAO.listaComTodosCandidatos()
    }
}
