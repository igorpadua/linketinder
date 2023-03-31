package com.igor.linketinder.controller

import com.google.gson.Gson
import com.igor.linketinder.dao.VagaDAO
import com.igor.linketinder.model.Competencia
import com.igor.linketinder.model.TipoCompetencia
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
@WebServlet(name = "vaga", urlPatterns = ["/vaga", "/vaga/*"])
class VagaController extends HttpServlet {

    private static final FabricaBanco fabricaBanco = new PostgesFabric()
    private static final VagaDAO vagaDAO = new VagaDAO(fabricaBanco)
    private static final Json json = new Json()

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            def vaga
            if (request.getPathInfo() != null) {
                Integer id = Integer.parseInt(request.getPathInfo().substring(1))
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
            final Integer id_empresa = jsonFormatado.id_empresa as int

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
            final Integer idVaga = Integer.parseInt(request.getPathInfo().substring(1))
            final LazyMap jsonFormatado = json.formataJson(request)

            Vaga vaga = criaVaga(jsonFormatado, idVaga)

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
            final Integer idVaga = Integer.parseInt(request.getPathInfo().substring(1))
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

    private static Vaga criaVaga(LazyMap jsonMap, Integer id = 0) {

        String nome = jsonMap.nome
        String descricao = jsonMap.descricao
        String local_vaga = jsonMap.local_vaga

        List<Competencia> competencias = []

        jsonMap.competencias.each { competencia ->
            String nomeCompetencia = competencia["competencia"]
            competencias.add(new Competencia(TipoCompetencia.valueOf(nomeCompetencia)))
        }

        return new Vaga(id, nome, descricao, local_vaga, competencias)
    }

    static Vaga pega(Integer idVaga) {
        return vagaDAO.pega(idVaga)
    }

    static void salvarNoBanco(Vaga vaga, Integer idEmpresa) {
        vagaDAO.salvar(vaga, idEmpresa)
    }

    static void atualizarNoBanco(Vaga vaga) {
        vagaDAO.atualiza(vaga)
    }

    static void removeDoBanco(Integer idVaga) {
        vagaDAO.remove(idVaga)
    }

    static List<Vaga> pegaVagas() {
        return vagaDAO.listaComTodasVagas()
    }
}
