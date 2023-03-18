package com.igor.linketinder.controller

import com.google.gson.Gson
import com.igor.linketinder.dao.EmpresaDAO
import com.igor.linketinder.model.Empresa
import com.igor.linketinder.dao.fabricaBanco.FabricaBanco
import com.igor.linketinder.dao.fabricaBanco.PostgesFabric
import com.igor.linketinder.util.Json
import com.igor.linketinder.util.Validacoes
import groovy.transform.TypeChecked
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.groovy.json.internal.LazyMap

import java.sql.SQLException

@TypeChecked
@WebServlet(name = "empresa", value = "/empresa")
class EmpresaController extends HttpServlet {

    private static final FabricaBanco fabricaBanco = FabricaBanco.criaInstancia(new PostgesFabric())
    private static final EmpresaDAO empresaDAO = new EmpresaDAO(fabricaBanco)
    private static final Json json = new Json()

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            def empresa
            if (request.getParameter("cnpj") != null) {
                String cnpj = request.getParameter("cnpj")
                empresa = pega(cnpj)
            } else {
                empresa = pegaEmpresas()
            }
            response.setContentType("application/json")
            response.setCharacterEncoding("UTF-8")

            if (empresa == null) {
                response.setStatus(404)
                response.setCharacterEncoding("UTF-8")
                response.getWriter().println("Empresa não encontrada")
                return
            }

            response.getWriter().println(new Gson().toJson(empresa))
        } catch (SQLException ignored) {
            response.setStatus(500)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Erro ao buscar empresa")
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LazyMap jsonFormatado = json.formataJson(request)
            final Empresa empresa = criaEmpresa(jsonFormatado)
            salvarNoBanco(empresa)
            response.getWriter().println("Empresa cadastrada com sucesso!")
            response.setStatus(200)
        } catch (SQLException ignored) {
            response.setStatus(500)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Erro ao cadastrar empresa")
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final String cnpj = request.getParameter("cnpj")
            final LazyMap jsonFormatado = json.formataJson(request)
            final Empresa empresa = criaEmpresa(jsonFormatado, cnpj)
            atualizarNoBanco(empresa)
            response.getWriter().println("Empresa atualizada com sucesso!")
            response.setStatus(200)
        } catch (SQLException ignored) {
            response.setStatus(500)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Erro ao atualizar empresa")
        }  catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final String cnpj = request.getParameter("cnpj")
            removeDoBanco(cnpj)
            response.getWriter().println("Empresa deletada com sucesso!")
            response.setStatus(201)
        } catch (SQLException ignored) {
            response.setStatus(500)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Erro ao deletar empresa")
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    private static Empresa criaEmpresa(LazyMap jsonFormatado, String CNPJ = null) {
        final String nome = jsonFormatado.nome
        final String email = jsonFormatado.email
        String cnpj
        if (CNPJ != null) {
            cnpj = CNPJ
        } else {
            cnpj = jsonFormatado.cnpj
        }
        Validacoes.validaCNPJ(cnpj)
        final String senha = jsonFormatado.senha
        final String pais = jsonFormatado.pais
        final String cep = jsonFormatado.cep
        Validacoes.validaCEP(cep)
        final String descricao = jsonFormatado.descricao
        return new Empresa(nome, email, cnpj, pais, cep, descricao, senha)
    }

    static void salvarNoBanco(Empresa empresa) {
        if (!Validacoes.validaCNPJ(empresa.cnpj)) throw new IllegalArgumentException("CNPJ inválido")
        if (!Validacoes.validaCEP(empresa.cep)) throw new IllegalArgumentException("CEP inválido")
        empresaDAO.salvar(empresa)
    }

    static Empresa pega(String cnpj) {
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
