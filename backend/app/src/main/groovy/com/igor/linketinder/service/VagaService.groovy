package com.igor.linketinder.service

import com.google.gson.Gson
import com.igor.linketinder.dao.VagaDAO
import com.igor.linketinder.dao.fabricaBanco.PostgesFabric
import com.igor.linketinder.model.Competencia
import com.igor.linketinder.model.Empresa
import com.igor.linketinder.model.Vaga
import groovy.json.JsonSlurper
import jakarta.servlet.*
import jakarta.servlet.http.*
import jakarta.servlet.annotation.*

@WebServlet(name = "vaga", value = "/vaga")
class VagaService extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            StringBuilder json = new StringBuilder()
            BufferedReader reader = request.getReader()
            String line
            while ((line = reader.readLine()) != null) {
                json.append(line)
            }

            JsonSlurper jsonSlurper = new JsonSlurper()
            def jsonMap = jsonSlurper.parseText(json.toString())
            Vaga vaga = new Vaga()
            vaga.nome = jsonMap.nome
            vaga.descricao = jsonMap.descricao
            vaga.local_vaga = jsonMap.local_vaga
            vaga.competencia = new Competencia()
            vaga.competencia.competencias = Competencia.transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(jsonMap.competencias)
            int id_empresa = jsonMap.id_empresa

            VagaDAO vagaDAO = new VagaDAO(new PostgesFabric())
            vagaDAO.salvar(vaga, id_empresa)

            response.setStatus(200)
        } catch (Exception e) {
            response.setStatus(500)
            e.printStackTrace()
        }
    }
}
