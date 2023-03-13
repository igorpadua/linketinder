package com.igor.linketinder.controller

import com.igor.linketinder.dao.EmpresaDAO
import com.igor.linketinder.model.Empresa
import com.igor.linketinder.fabricaBanco.FabricaBanco
import com.igor.linketinder.fabricaBanco.PostgesFabric
import com.igor.linketinder.model.Pessoa
import com.igor.linketinder.util.Validacoes
import com.igor.linketinder.view.EmpresaView
import com.igor.linketinder.view.PessoaView
import groovy.transform.TypeChecked

@TypeChecked
class EmpresaController {

    private static final FabricaBanco fabricaBanco = FabricaBanco.criaInstancia(new PostgesFabric())
    private static final EmpresaDAO empresaDAO = new EmpresaDAO(fabricaBanco)

    static void adicionar() {
        Empresa empresa = EmpresaView.cadastro()
        salvarNoBanco(empresa)
        EmpresaView.adicionadoComSucesso()
    }

    static void atualizar() {
        final String CNPJ = EmpresaView.pegaCnpj()
        final Empresa empresa = pega(CNPJ)
        if (empresa == null) throw new IllegalArgumentException("Empresa não encontrada")
        EmpresaView.atualizar(empresa)
        atualizarNoBanco(empresa)
        EmpresaView.atualizadoComSucesso()
    }

    static void remover() {
        final String CNPJ = EmpresaView.pegaCnpj()
        final Empresa empresa = pega(CNPJ)
        if (empresa == null) throw new IllegalArgumentException("Empresa não encontrada")
        removeDoBanco(CNPJ)
        EmpresaView.removidoComSucesso()
    }

    static void listar() {
        List<Pessoa> empresas = pegaEmpresas() as List<Pessoa>
        PessoaView.lista(empresas)
    }

    static void salvarNoBanco(Empresa empresa) {
        if (!Validacoes.validaCNPJ(empresa.cnpj)) throw new IllegalArgumentException("CNPJ inválido")
        if (!Validacoes.validaCEP(empresa.cep)) throw new IllegalArgumentException("CEP inválido")
        empresaDAO.salvar(empresa)
    }

    static Empresa pega(String cnpj) {
        return empresaDAO.pegar(cnpj)
    }

    static int pegaId(String cnpj) {
        return empresaDAO.pegaId(cnpj)
    }

    static Empresa pegaEmpresaPeloCnpj(String cnpj) {
        return empresaDAO.pegar(cnpj)
    }

    static void atualizarNoBanco(Empresa empresa) {
        empresaDAO.atualiza(empresa)
    }

    static void removeDoBanco(String cnpj) {
        empresaDAO.remove(cnpj)
    }

    static List<Empresa> pegaEmpresas() {
        return empresaDAO.listaComTodasEmpresas()
    }
}
