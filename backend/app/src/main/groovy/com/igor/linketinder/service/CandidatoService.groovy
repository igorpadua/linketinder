package com.igor.linketinder.service

import com.google.gson.Gson
import com.igor.linketinder.controller.CandidatoController
import com.igor.linketinder.dao.CandidatoDAO
import com.igor.linketinder.dao.fabricaBanco.PostgesFabric
import com.igor.linketinder.model.Candidato
import com.igor.linketinder.model.Competencia
import com.igor.linketinder.util.Json
import jakarta.servlet.*
import jakarta.servlet.http.*
import jakarta.servlet.annotation.*

import java.text.SimpleDateFormat

@WebServlet(name = "candidato", value = "/candidato")
class CandidatoService extends HttpServlet {
    private static final Json json = new Json()
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            def candidato
            if (request.getParameter("cpf") != null) {
                String cpf = request.getParameter("cpf")
                candidato = pegaCandidato(cpf)
            } else {
                candidato = CandidatoController.pegaCandidos()
            }
            response.setContentType("application/json")
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println(new Gson().toJson(candidato))
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            def jsonFormatado = json.formataJson(request)

            final Candidato candidato = criaCandidato(jsonFormatado)

            CandidatoController.salvarNoBanco(candidato)

            response.getWriter().println("Candidato cadastrado com sucesso!")
            response.setStatus(200)
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final String cpf = request.getParameter("cpf")
            final def jsonFormatado = json.formataJson(request)
            final Candidato candidato = criaCandidato(jsonFormatado, cpf)
            CandidatoController.atualizarNoBanco(candidato)
            response.getWriter().println("Candidato atualizado com sucesso!")
            response.setStatus(200)
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final String cpf = request.getParameter("cpf")
            CandidatoController.removeNoBanco(cpf)
            response.getWriter().println("Candidato deletado com sucesso!")
            response.setStatus(200)
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    private Candidato pegaCandidato(String cpf) {
        CandidatoDAO candidatoDAO = new CandidatoDAO(new PostgesFabric())
        return candidatoDAO.pegar(cpf)
    }

    private Candidato criaCandidato(def jsonFormatado, String CPF = null) {
        String nome = jsonFormatado.nome
        String sobrenome = jsonFormatado.sobrenome
        String stringData = jsonFormatado.nascimento
        Date nascimento = new SimpleDateFormat("dd/MM/yyyy").parse(stringData)
        String email = jsonFormatado.email
        String cpf
        if (CPF != null) {
            cpf = CPF
        } else {
            cpf = jsonFormatado.cpf
        }
        String pais = jsonFormatado.pais
        String cep = jsonFormatado.cep
        String descricao = jsonFormatado.descricao
        String senha = jsonFormatado.senha
        String competencias = jsonFormatado.competencias
        Competencia competencia = new Competencia()
        competencia.competencias = Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(competencias)
        return new Candidato(nome, sobrenome, nascimento, email, cpf, pais, cep, descricao, senha, competencia)
    }
}
