package main.groovy.dto

import main.groovy.entity.Candidato
import groovy.sql.Sql
import groovy.transform.TypeChecked
import main.groovy.entity.Competencia
import main.groovy.service.CompetenciaService

import java.text.SimpleDateFormat

@TypeChecked
class CandidatoDTO {
    final url = 'jdbc:postgresql://localhost/liketinder'
    final user= 'postgres'
    final password= '123456'
    final drive= "org.postgresql.Driver"

    void inserirCandidato(Candidato candidato) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeInsert('INSERT INTO candidatos ' +
                '(nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha) ' +
                "VALUES ('${candidato.nome}', '${candidato.sobrenome}', '${candidato.nascimento.dateString}', " +
                "'${candidato.email}', '${candidato.cpf}', '${candidato.pais}', '${candidato.cep}', " +
                "'${candidato.desc}', '${candidato.senha}')")
        sql.close()
    }

    void removeCandidato(String cpf) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeInsert('DELETE FROM candidatos ' +
                "WHERE cpf = '${cpf}'")
        sql.close()
    }

    List<Candidato> listaTodosCandidatos() {
        Sql sql = Sql.newInstance(url, user, password, drive)
        List<Candidato> aux = new ArrayList<>()
        sql.eachRow("""SELECT c.nome, c.sobrenome, c.data_nascimento, c.email, c.cpf,
                            c.pais, c.cep, c.descricao, c.senha, array_agg(competencia.competencia) as competencias
                            FROM candidatos c
                            INNER JOIN competencias_candidato cc ON cc.candidatos_id = c.id
                            INNER JOIN competencias competencia ON competencia.id = cc.competencia_id
                            GROUP BY c.id;""") { rs ->
            // Remove os {} do array de competencias
            String competencias = rs.getString('competencias').replace('{', '').replace('}', '')
            // Separa as competencias por virgula
            String[] competenciasArray = competencias.split(',')
            // Transforma cada competencia
            List<Competencia> competenciasList = new ArrayList<>()
            for (competencia in competenciasArray) {
                competenciasList.add(CompetenciaService.transformaString(competencia))
            }
            Candidato candidato = new Candidato(rs.getString('nome').trim(), rs.getString('sobrenome'),
                    rs.getDate('data_nascimento'), rs.getString('email'), rs.getString('cpf'),
                    rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'),
                    rs.getString('senha'), competenciasList)
            aux.add(candidato)
        }
        sql.close()

        return aux
    }

}
