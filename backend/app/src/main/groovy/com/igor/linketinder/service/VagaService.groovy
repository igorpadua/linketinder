package com.igor.linketinder.service

import com.google.gson.Gson
import com.igor.linketinder.controller.VagaController
import com.igor.linketinder.dao.VagaDAO
import com.igor.linketinder.dao.fabricaBanco.PostgesFabric
import com.igor.linketinder.model.Competencia
import com.igor.linketinder.model.Vaga
import com.igor.linketinder.util.Json
import jakarta.servlet.*
import jakarta.servlet.http.*
import jakarta.servlet.annotation.*

@WebServlet(name = "vaga", value = "/vaga")
class VagaService extends HttpServlet {

    private static final Json json = new Json()

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            def vaga
            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"))
                vaga = buscarVagaPorId(id)
            } else {
                vaga = VagaController.pegaVagas()
            }
            response.setContentType("application/json")
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println(new Gson().toJson(vaga))
        } catch (Exception e) {
            response.setStatus(500)
            response.getWriter().println("Erro ao listar vagas: " + e.getMessage())
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            def jsonFormatado = json.formataJson(request)

            final Vaga vaga = criaVaga(jsonFormatado)
            final int id_empresa = jsonFormatado.id_empresa

            VagaController.salvarNoBanco(vaga, id_empresa)

            response.getWriter().println("Vaga cadastrada com sucesso!")
            response.setStatus(200)
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final def jsonFormatado= json.formataJson(request)

            final String idVaga = request.getParameter("id")

            Vaga vaga = criaVaga(jsonFormatado)
            vaga.id = Integer.parseInt(idVaga)

            VagaController.atualizarNoBanco(vaga)

            response.getWriter().println("Vaga atualizada com sucesso!")
            response.setStatus(200)
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final int idVaga = Integer.parseInt(request.getParameter("id"))

            VagaController.removeDoBanco(idVaga)

            response.getWriter().println("Vaga deletada com sucesso!")
            response.setStatus(200)
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    private Vaga buscarVagaPorId(int id) {
        VagaDAO vagaDAO = new VagaDAO(new PostgesFabric())
        Vaga vaga = vagaDAO.pega(id)
        return vaga
    }

    private Vaga criaVaga(def jsonMap) {
        String nome = jsonMap.nome
        String descricao = jsonMap.descricao
        String local_vaga = jsonMap.local_vaga
        String competencias = jsonMap.competencias
        Competencia competencia = new Competencia()
        competencia.competencias = Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(competencias)
        return new Vaga(0, nome, descricao, local_vaga, competencia)
    }

}
