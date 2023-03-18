package com.igor.linketinder.controller

import com.google.gson.Gson
import com.igor.linketinder.dao.CompetenciaVagasDAO
import com.igor.linketinder.dao.VagaDAO
import com.igor.linketinder.model.Competencia
import com.igor.linketinder.model.Vaga
import com.igor.linketinder.dao.fabricaBanco.FabricaBanco
import com.igor.linketinder.dao.fabricaBanco.PostgesFabric
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
@WebServlet(name = "vaga", value = "/vaga")
class VagaController extends HttpServlet {

    private static final FabricaBanco fabricaBanco = FabricaBanco.criaInstancia(new PostgesFabric())
    private static final VagaDAO vagaDAO = new VagaDAO(fabricaBanco)
    private static final CompetenciaVagasDAO competenciaVagasDAO = new CompetenciaVagasDAO(fabricaBanco)
    private static final Json json = new Json()

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            def vaga
            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"))
                vaga = pega(id)
            } else {
                vaga = pegaVagas()
            }
            response.setContentType("application/json")
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println(new Gson().toJson(vaga))
        } catch (SQLException ignored) {
            response.setStatus(500)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Erro ao buscar vaga")
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LazyMap jsonFormatado = json.formataJson(request)

            final Vaga vaga = criaVaga(jsonFormatado)
            final int id_empresa = jsonFormatado.id_empresa as int

            salvarNoBanco(vaga, id_empresa)

            response.getWriter().println("Vaga cadastrada com sucesso!")
            response.setStatus(201)
        } catch (SQLException ignored) {
            response.setStatus(500)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Erro ao cadastrar vaga")
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final LazyMap jsonFormatado = json.formataJson(request)

            final String idVaga = request.getParameter("id")

            Vaga vaga = criaVaga(jsonFormatado)
            vaga.id = Integer.parseInt(idVaga)

            atualizarNoBanco(vaga)

            response.getWriter().println("Vaga atualizada com sucesso!")
            response.setStatus(200)
        } catch (SQLException ignored) {
            response.setStatus(500)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Erro ao atualizar vaga")
        }  catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final int idVaga = Integer.parseInt(request.getParameter("id"))
            removeDoBanco(idVaga)
            response.getWriter().println("Vaga deletada com sucesso!")
            response.setStatus(201)
        } catch (SQLException ignored) {
            response.setStatus(500)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Erro ao deletar vaga")
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    private static Vaga criaVaga(LazyMap jsonMap) {
        String nome = jsonMap.nome
        String descricao = jsonMap.descricao
        String local_vaga = jsonMap.local_vaga
        String competencias = jsonMap.competencias
        Competencia competencia = new Competencia()
        competencia.competencias = Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(competencias)
        return new Vaga(0, nome, descricao, local_vaga, competencia)
    }

    static Vaga pega(int idVaga) {
        return vagaDAO.pega(idVaga)
    }

    static void salvarNoBanco(Vaga vaga, int idEmpresa) {
        vagaDAO.salvar(vaga, idEmpresa)
        vaga.id = vagaDAO.pegaId(vaga)
        competenciaVagasDAO.salvar(vaga)
    }

    static void atualizarNoBanco(Vaga vaga) {
        vagaDAO.atualiza(vaga)
        competenciaVagasDAO.atualizar(vaga)
    }

    static void removeDoBanco(int idVaga) {
        vagaDAO.remove(idVaga)
    }

    static List<Vaga> pegaVagas() {
        return vagaDAO.listaComTodasVagas()
    }
}
