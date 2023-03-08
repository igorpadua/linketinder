package com.igor.linketinder.controller

import com.igor.linketinder.dao.EmpresaDAO
import com.igor.linketinder.entity.Empresa
import com.igor.linketinder.service.EmpresaService
import com.igor.linketinder.service.PessoaService

class EmpresaController {
    static void adicionar() {
        Empresa empresa = EmpresaService.criar()
        EmpresaDAO.adicionar(empresa)
        println("\nAdiciona com sucesso\n")
    }

    static void atualizar() {
        final String cnpj = EmpresaService.pegaCnpj()
        Empresa empresa = EmpresaDAO.pega(cnpj)
        EmpresaService.atualizar(empresa)
        EmpresaDAO.atualiza(empresa)
        println("\nAtualizado com sucesso\n")
    }

    static void remover() {
        final String cnpj = EmpresaService.pegaCnpj()
        EmpresaDAO.remove(cnpj)
        println("\nRemovido com sucesso\n")
    }

    static void listar() {
        PessoaService.imprimir(EmpresaDAO.listaComTodasEmpresas())
    }
}
