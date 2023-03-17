package com.igor.linketinder.service

import com.google.gson.Gson
import com.igor.linketinder.controller.EmpresaController
import com.igor.linketinder.model.Empresa
import com.igor.linketinder.util.Json
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "empresa", value = "/empresa")
class EmpresaService extends HttpServlet {
    private static final Json json = new Json()

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            def empresa
            if (request.getParameter("cnpj") != null) {
                String cnpj = request.getParameter("cnpj")
                empresa = EmpresaController.pega(cnpj)
            } else {
                empresa = EmpresaController.pegaEmpresas()
            }
            response.setContentType("application/json")
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println(new Gson().toJson(empresa))
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            def jsonFormatado = json.formataJson(request)
            final Empresa empresa = criaEmpresa(jsonFormatado)
            EmpresaController.salvarNoBanco(empresa)
            response.getWriter().println("Empresa cadastrada com sucesso!")
            response.setStatus(200)
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final String cnpj = request.getParameter("cnpj")
            final def jsonFormatado = json.formataJson(request)
            final Empresa empresa = criaEmpresa(jsonFormatado, cnpj)
            EmpresaController.atualizarNoBanco(empresa)
            response.getWriter().println("Empresa atualizada com sucesso!")
            response.setStatus(200)
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final String cnpj = request.getParameter("cnpj")
            EmpresaController.removeDoBanco(cnpj)
            response.getWriter().println("Empresa deletada com sucesso!")
            response.setStatus(200)
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    private Empresa criaEmpresa(def jsonFormatado, String CNPJ = null) {
        final String nome = jsonFormatado.nome
        final String email = jsonFormatado.email
        String cnpj
        if (CNPJ != null) {
            cnpj = CNPJ
        } else {
            cnpj = jsonFormatado.cnpj
        }
        final String senha = jsonFormatado.senha
        final String pais = jsonFormatado.pais
        final String cep = jsonFormatado.cep
        final String descricao = jsonFormatado.descricao
        return new Empresa(nome, email, cnpj, pais, cep, descricao, senha)
    }
}
