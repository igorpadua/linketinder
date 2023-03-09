package com.igor.linketinder.controller

import com.igor.linketinder.dao.EmpresaDAO
import com.igor.linketinder.entity.Empresa
import com.igor.linketinder.view.EmpresaView
import com.igor.linketinder.view.PessoaView

class EmpresaController {
    static void adicionar() {
        Empresa empresa = EmpresaView.criar()
        EmpresaDAO.adicionar(empresa)
        println("\nAdiciona com sucesso\n")
    }

    static void atualizar() {
        final String cnpj = EmpresaView.pegaCnpj()
        Empresa empresa = EmpresaDAO.pega(cnpj)
        EmpresaView.atualizar(empresa)
        EmpresaDAO.atualiza(empresa)
        println("\nAtualizado com sucesso\n")
    }

    static void remover() {
        final String cnpj = EmpresaView.pegaCnpj()
        EmpresaDAO.remove(cnpj)
        println("\nRemovido com sucesso\n")
    }

    static void listar() {
        PessoaView.imprimir(EmpresaDAO.listaComTodasEmpresas())
    }
}
