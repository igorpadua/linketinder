package com.igor.linketinder.dao

import com.igor.linketinder.dao.fabricaBanco.FabricaBanco
import com.igor.linketinder.model.Curtida
import groovy.sql.Sql
import groovy.transform.TypeChecked

@TypeChecked
class CurtidaVagaDAO {

        private Sql sql

        CurtidaVagaDAO(FabricaBanco fabricaBanco) {
            this.sql = fabricaBanco.iniciarBancoDeDados().conectar()
        }

        void salvar(Curtida curtida) {
            sql.executeInsert("INSERT INTO curtida_vaga (candidato_id, vaga_id) " +
                    "VALUES (${curtida.idUsuario}, ${curtida.idLink})")
        }

        void remove(Curtida curtida) {
            sql.execute("DELETE FROM curtida_vaga " +
                    "WHERE candidato_id = ${curtida.idUsuario} AND vaga_id = ${curtida.idLink}")
        }

        List<Curtida> listaComTodasCurtidas() {
            List<Curtida> result = new ArrayList<>()
            sql.eachRow('SELECT * FROM curtida_vaga') { rs ->
                Curtida curtida = new Curtida(rs.getInt('candidato_id'), rs.getInt('vaga_id'))
                result.add(curtida)
            }
            return result
        }
}
