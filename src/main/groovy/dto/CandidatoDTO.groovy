package main.groovy.dto

import main.groovy.entity.Candidato
import groovy.sql.Sql
import groovy.transform.TypeChecked
import main.groovy.entity.Competencia
import main.groovy.service.CompetenciaService

import java.text.SimpleDateFormat

@TypeChecked
class CandidatoDTO {
    static final url = 'jdbc:postgresql://localhost/liketinder'
    static final user= 'postgres'
    static final password= '123456'
    static final drive= "org.postgresql.Driver"

    static void inserirCandidato(Candidato candidato) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeInsert('INSERT INTO candidatos ' +
                '(nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha) ' +
                "VALUES ('${candidato.nome}', '${candidato.sobrenome}', '${candidato.nascimento.dateString}', " +
                "'${candidato.email}', '${candidato.cpf}', '${candidato.pais}', '${candidato.cep}', " +
                "'${candidato.desc}', '${candidato.senha}')")
        sql.close()
    }

    static void atualizarCandidato(Candidato candidato) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeInsert('UPDATE candidatos ' +
                "SET nome = '${candidato.nome}', sobrenome = '${candidato.sobrenome}', " +
                "data_nascimento = '${candidato.nascimento.dateString}', email = '${candidato.email}', " +
                "cpf = '${candidato.cpf}', pais = '${candidato.pais}', cep = '${candidato.cep}', " +
                "descricao = '${candidato.desc}', senha = '${candidato.senha}' " +
                "WHERE cpf = '${candidato.cpf}'")
        sql.close()
    }

    static void removeCandidato(String cpf) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeInsert('DELETE FROM candidatos ' +
                "WHERE cpf = '${cpf}'")
        sql.close()
    }

    static private List<Competencia> arrayCompetencia(String competencias) {
        // Remove os {} do array de competencias
        String remover = competencias.replace('{', '').replace('}', '')
        // Separa as competencias por virgula
        String[] competenciasArray = remover.split(',')
        // Transforma cada competencia
        List<Competencia> competenciasList = new ArrayList<>()
        for (String competencia in competenciasArray) {
            competenciasList.add(CompetenciaService.transformaString(competencia))
        }
        return competenciasList
    }

    static Candidato getCandidato(String cpf) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        Candidato aux = null
        sql.eachRow("""SELECT c.nome, c.sobrenome, c.data_nascimento, c.email, c.cpf,
                            c.pais, c.cep, c.descricao, c.senha, array_agg(competencia.competencia) as competencias
                            FROM candidatos c
                            INNER JOIN competencias_candidato cc ON cc.candidatos_id = c.id
                            INNER JOIN competencias competencia ON competencia.id = cc.competencia_id
                            WHERE c.cpf = ${cpf}
                            GROUP BY c.id;""") { rs ->
            List<Competencia> competenciasList = new ArrayList<>(arrayCompetencia(rs.getString('competencias')))
            // Transforma a data de String para Date
            Date data = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString('data_nascimento'))
            aux = new Candidato(rs.getString('nome'), rs.getString('sobrenome'), data, rs.getString('email'),
                    rs.getString('cpf'), rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'),
                    rs.getString('senha'), competenciasList)
        }
        sql.close()
        return aux
    }

    static List<Candidato> listaTodosCandidatos() {
        Sql sql = Sql.newInstance(url, user, password, drive)
        List<Candidato> aux = new ArrayList<>()
        sql.eachRow("""SELECT c.nome, c.sobrenome, c.data_nascimento, c.email, c.cpf,
                            c.pais, c.cep, c.descricao, c.senha, array_agg(competencia.competencia) as competencias
                            FROM candidatos c
                            INNER JOIN competencias_candidato cc ON cc.candidatos_id = c.id
                            INNER JOIN competencias competencia ON competencia.id = cc.competencia_id
                            GROUP BY c.id;""") { rs ->
            List<Competencia> competenciasList = new ArrayList<>(arrayCompetencia(rs.getString('competencias')))
            Candidato candidato = new Candidato(rs.getString('nome').trim(), rs.getString('sobrenome'),
                    rs.getDate('data_nascimento'), rs.getString('email'), rs.getString('cpf'),
                    rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'),
                    rs.getString('senha'), competenciasList)
            aux.add(candidato)
        }
        sql.close()
        return aux
    }

    static int getIdCandidato(String cpf) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        int aux = 0
        sql.eachRow("""SELECT c.id
                            FROM candidatos c
                            WHERE c.cpf = ${cpf};""") { rs ->
            aux = rs.getInt('id')
        }
        sql.close()
        return aux
    }
}
