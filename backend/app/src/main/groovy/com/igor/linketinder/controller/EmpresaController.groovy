package com.igor.linketinder.controller

import com.igor.linketinder.dao.EmpresaDAO
import com.igor.linketinder.model.Empresa
import com.igor.linketinder.fabricaBanco.FabricaBanco
import com.igor.linketinder.fabricaBanco.PostgesFabric
import com.igor.linketinder.view.EmpresaView
import com.igor.linketinder.view.PessoaView

class EmpresaController {

    private static final FabricaBanco fabricaBanco = FabricaBanco.criaInstancia(new PostgesFabric())
    private static final EmpresaDAO empresaDAO = new EmpresaDAO(fabricaBanco)

    static void adicionar() {
        Empresa empresa = EmpresaView.criar()
        empresaDAO.salvar(empresa)
        println("\nAdiciona com sucesso\n")
    }

    static void atualizar() {
        final String cnpj = EmpresaView.pegaCnpj()
        Empresa empresa = empresaDAO.pegar(cnpj)
        EmpresaView.atualizar(empresa)
        empresaDAO.atualiza(empresa)
        println("\nAtualizado com sucesso\n")
    }

    static void remover() {
        final String cnpj = EmpresaView.pegaCnpj()
        empresaDAO.remove(cnpj)
        println("\nRemovido com sucesso\n")
    }

    static void listar() {
        PessoaView.imprimir(empresaDAO.listaComTodasEmpresas())
    }
}
