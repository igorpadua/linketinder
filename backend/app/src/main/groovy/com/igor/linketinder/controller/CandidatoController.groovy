package com.igor.linketinder.controller

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.igor.linketinder.dao.CandidatoDAO
import com.igor.linketinder.model.Candidato
import com.igor.linketinder.dao.fabricaBanco.FabricaBanco
import com.igor.linketinder.dao.fabricaBanco.PostgesFabric
import com.igor.linketinder.model.Competencia
import com.igor.linketinder.model.TipoCompetencia
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
import java.text.SimpleDateFormat

@TypeChecked
@WebServlet(name = "candidato", urlPatterns = ["/candidato", "/candidato/*"])
class CandidatoController extends HttpServlet {

    private static final FabricaBanco fabricaBanco = new PostgesFabric()
    private static final CandidatoDAO candidatoDAO = new CandidatoDAO(fabricaBanco)
    private static final Json json = new Json()

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            def candidato
            if (request.getPathInfo() != null) {
                Integer id = Integer.parseInt(request.getPathInfo().substring(1))
                candidato = pega(id)
            } else {
                candidato = pegaCandidos()
            }
            response.setContentType("application/json")
            response.setCharacterEncoding("UTF-8")

            if (candidato == null) {
                response.setStatus(404)
                response.setCharacterEncoding("UTF-8")
                response.getWriter().println("Candidato não encontrado")
                return
            }

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create()
            response.getWriter().println(gson.toJson(candidato))
            response.setStatus(200)
        } catch (SQLException ignored) {
            response.setStatus(500)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Erro ao buscar candidato")
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LazyMap jsonFormatado = json.formataJson(request)

            final Candidato candidato = criaCandidato(jsonFormatado)
            salvarNoBanco(candidato)

            response.getWriter().println("Candidato cadastrado com sucesso!")
            response.setStatus(201)
        } catch (SQLException ignored) {
            response.setStatus(500)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Erro ao cadastrar candidato")
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final Integer id = Integer.parseInt(request.getPathInfo().substring(1))
            final LazyMap jsonFormatado = json.formataJson(request)
            final Candidato candidato = criaCandidato(jsonFormatado, id)
            atualizarNoBanco(candidato)
            response.getWriter().println("Candidato atualizado com sucesso!")
            response.setStatus(200)
        } catch (SQLException ignored) {
            response.setStatus(500)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Erro ao atualizar candidato")
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final Integer id = Integer.parseInt(request.getPathInfo().substring(1))
            removeNoBanco(id)
            response.getWriter().println("Candidato deletado com sucesso!")
            response.setStatus(200)
        }  catch (SQLException ignored) {
            response.setStatus(500)
            response.setCharacterEncoding("UTF-8")
            response.getWriter().println("Erro ao deletar candidato")
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    private static Candidato criaCandidato(LazyMap jsonFormatado, Integer id = 0) {

        String nome = jsonFormatado.nome
        String sobrenome = jsonFormatado.sobrenome
        String stringData = jsonFormatado.nascimento
        Date nascimento = new SimpleDateFormat("dd/MM/yyyy").parse(stringData)
        String email = jsonFormatado.email
        String cpf = jsonFormatado.cpf
        Validacoes.validaCPF(cpf)
        String pais = jsonFormatado.pais
        String cep = jsonFormatado.cep
        Validacoes.validaCEP(cep)
        String descricao = jsonFormatado.descricao
        String senha = jsonFormatado.senha

        List<Competencia> competencias = []
        jsonFormatado.competencias.each { competencia ->
            String nomeCompetencia = competencia["competencia"]
            competencias.add(new Competencia(TipoCompetencia.valueOf(nomeCompetencia)))
        }

        return new Candidato(id, nome, sobrenome, nascimento, email, cpf, pais, cep, descricao, senha, competencias)
    }

    static void salvarNoBanco(Candidato candidato) {
        if (!Validacoes.validaCPF(candidato.cpf)) throw new IllegalArgumentException("CPF inválido")
        if (!Validacoes.validaCEP(candidato.cep)) throw new IllegalArgumentException("CEP inválido")
        candidatoDAO.salvar(candidato)
    }

    static Candidato pega(Integer idCandidato) {
        return candidatoDAO.pegar(idCandidato)
    }

    static void atualizarNoBanco(Candidato candidato) {
        candidatoDAO.atualiza(candidato)
    }

    static void removeNoBanco(Integer id) {
        candidatoDAO.remove(id)
    }

    static List<Candidato> pegaCandidos() {
        return candidatoDAO.listaComTodosCandidatos()
    }
}
