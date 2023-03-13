package com.igor.linketinder.controller

import com.igor.linketinder.dao.EmpresaDAO
import com.igor.linketinder.model.Empresa
import com.igor.linketinder.fabricaBanco.FabricaBanco
import com.igor.linketinder.fabricaBanco.PostgesFabric
import com.igor.linketinder.util.Validacoes
import groovy.transform.TypeChecked

@TypeChecked
class EmpresaController {

    private static final FabricaBanco fabricaBanco = FabricaBanco.criaInstancia(new PostgesFabric())
    private static final EmpresaDAO empresaDAO = new EmpresaDAO(fabricaBanco)

    static void savarNoBanco(Empresa empresa) {
        if (!Validacoes.validaCNPJ(empresa.cnpj)) throw new IllegalArgumentException("CNPJ inválido")
        if (!Validacoes.validaCEP(empresa.cep)) throw new IllegalArgumentException("CEP inválido")
        empresaDAO.salvar(empresa)
    }

    static Empresa pegaCnpj(String cnpj) {
        return empresaDAO.pegar(cnpj)
    }

    static void atualizarNoBanco(Empresa empresa) {
        empresaDAO.atualiza(empresa)
    }

    static void remover(String cnpj) {
        empresaDAO.remove(cnpj)
    }

    static List<Empresa> listar() {
        return empresaDAO.listaComTodasEmpresas()
    }
}
