package com.igor.linketinder.controller

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.igor.linketinder.dao.CandidatoDAO
import com.igor.linketinder.dao.CompetenciaCandidatoDAO
import com.igor.linketinder.model.Candidato
import com.igor.linketinder.dao.fabricaBanco.FabricaBanco
import com.igor.linketinder.dao.fabricaBanco.PostgesFabric
import com.igor.linketinder.model.Competencia
import com.igor.linketinder.util.Json
import com.igor.linketinder.util.Validacoes
import groovy.transform.TypeChecked
import jakarta.servlet.ServletException
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.groovy.json.internal.LazyMap

import java.text.SimpleDateFormat

@TypeChecked
@WebServlet(name = "candidato", value = "/candidato")
class CandidatoController extends HttpServlet {

    private static final FabricaBanco fabricaBanco = FabricaBanco.criaInstancia(new PostgesFabric())
    private static final CandidatoDAO candidatoDAO = new CandidatoDAO(fabricaBanco)
    private static final CompetenciaCandidatoDAO competenciaCandidatoDAO = new CompetenciaCandidatoDAO(fabricaBanco)
    private static final Json json = new Json()

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            def candidato
            if (request.getParameter("cpf") != null) {
                String cpf = request.getParameter("cpf")
                candidato = pega(cpf)
            } else {
                candidato = pegaCandidos()
            }
            response.setContentType("application/json")
            response.setCharacterEncoding("UTF-8")
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create()
            response.getWriter().println(gson.toJson(candidato))
            response.setStatus(200)
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
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final String cpf = request.getParameter("cpf")
            final LazyMap jsonFormatado = json.formataJson(request)
            final Candidato candidato = criaCandidato(jsonFormatado, cpf)
            atualizarNoBanco(candidato)
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
            removeNoBanco(cpf)
            response.getWriter().println("Candidato deletado com sucesso!")
            response.setStatus(200)
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }

    private static Candidato criaCandidato(LazyMap jsonFormatado, String CPF = null) {
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
        Validacoes.validaCPF(cpf)
        String pais = jsonFormatado.pais
        String cep = jsonFormatado.cep
        Validacoes.validaCEP(cep)
        String descricao = jsonFormatado.descricao
        String senha = jsonFormatado.senha
        String competencias = jsonFormatado.competencias
        Competencia competencia = new Competencia()
        competencia.competencias = Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(competencias)
        return new Candidato(nome, sobrenome, nascimento, email, cpf, pais, cep, descricao, senha, competencia)
    }

    static void salvarNoBanco(Candidato candidato) {
        if (!Validacoes.validaCPF(candidato.cpf)) throw new IllegalArgumentException("CPF inválido")
        if (!Validacoes.validaCEP(candidato.cep)) throw new IllegalArgumentException("CEP inválido")
        candidatoDAO.salvar(candidato)
        competenciaCandidatoDAO.salvar(candidato)
    }


    static Candidato pega(String cpf) {
        return candidatoDAO.pegar(cpf)
    }

    static void atualizarNoBanco(Candidato candidato) {
        candidatoDAO.atualiza(candidato)
        competenciaCandidatoDAO.atualizar(candidato)
    }

    static void removeNoBanco(String cpf) {
        competenciaCandidatoDAO.remove(cpf)
        candidatoDAO.remove(cpf)
    }

    static List<Candidato> pegaCandidos() {
        return candidatoDAO.listaComTodosCandidatos()
    }
}
