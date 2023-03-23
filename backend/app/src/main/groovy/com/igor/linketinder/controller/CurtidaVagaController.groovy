package com.igor.linketinder.controller

import com.google.gson.Gson
import com.igor.linketinder.dao.CurtidaVagaDAO
import com.igor.linketinder.dao.fabricaBanco.FabricaBanco
import com.igor.linketinder.dao.fabricaBanco.PostgesFabric
import com.igor.linketinder.model.Curtida
import com.igor.linketinder.util.Json
import groovy.transform.TypeChecked
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.groovy.json.internal.LazyMap

import java.sql.SQLException

@TypeChecked
@WebServlet(name = "curtidaVaga", value = "/curtidaVaga")
class CurtidaVagaController extends HttpServlet {

    private static final FabricaBanco fabricaBanco = new PostgesFabric()
    private static final CurtidaVagaDAO curtidaVagaDAO = new CurtidaVagaDAO(fabricaBanco)
    private static final Json json = new Json()

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final List<Curtida> curtidaVagas = pegaCurtidas()
            response.setContentType("application/json")
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println(new Gson().toJson(curtidaVagas))
        } catch (SQLException ignored) {
            response.setStatus(500)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Erro ao buscar candidato")
        } catch (Exception ignored) {
            response.setStatus(500)
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LazyMap jsonFormado = json.formataJson(request)

            final Curtida curtida = criaCurtida(jsonFormado)

            curtidaVagaDAO.salvar(curtida)

            response.setStatus(201)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Curtida realizada com sucesso")
        } catch (SQLException ignored) {
            response.setStatus(500)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Erro ao curtir")
        } catch (Exception ignored) {
            response.setStatus(500)
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LazyMap jsonFormado = json.formataJson(request)

            final Curtida curtida = criaCurtida(jsonFormado)

            curtidaVagaDAO.remove(curtida)

            response.setStatus(201)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Curtida deletada com sucesso")
        } catch (SQLException ignored) {
            response.setStatus(500)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Erro ao deletar curtida")
        } catch (Exception ignored) {
            response.setStatus(500)
        }
    }

    static List<Curtida> pegaCurtidas() throws SQLException {
        return curtidaVagaDAO.listaComTodasCurtidas()
    }

    private static Curtida criaCurtida(LazyMap jsonFormado) {
        int idUsuario = jsonFormado.get("idUsuario") as int
        int idLink = jsonFormado.get("idLink") as int
        new Curtida(idUsuario, idLink)
    }
}
