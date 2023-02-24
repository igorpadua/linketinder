package main.groovy.dto

import groovy.sql.Sql

class CompetenciaCandidatoDTO {
    final url = 'jdbc:postgresql://localhost/liketinder'
    final user= 'postgres'
    final password= '123456'
    final drive= "org.postgresql.Driver"

    void inserirCompetenciaCandidato(int idCandidato, int idCompetencia) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeInsert('INSERT INTO competencias_candidatos ' +
                '(id_candidato, id_competencia) ' +
                "VALUES ('${idCandidato}', '${idCompetencia}')")
        sql.close()
    }

    void removeCompetenciaCandidato(int idCandidato, int idCompetencia) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeInsert('DELETE FROM competencias_candidatos ' +
                "WHERE id_candidato = '${idCandidato}' AND id_competencia = '${idCompetencia}'")
        sql.close()
    }
}
