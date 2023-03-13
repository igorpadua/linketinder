package com.igor.linketinder.dao

import com.igor.linketinder.dao.fabricaBanco.FabricaBanco
import groovy.sql.Sql
import groovy.transform.TypeChecked
import com.igor.linketinder.model.Empresa

@TypeChecked
class EmpresaDAO {

    private Sql sql

    EmpresaDAO(FabricaBanco fabricaBanco) {
        this.sql = fabricaBanco.iniciarBancoDeDados().conectar()
    }

    static private void validaEmpresas(Empresa empresa)  {
        if (empresa == null) {
            throw new IllegalArgumentException("Não existe empresa com esse CNPJ")
        }
    }

    void salvar(Empresa empresa) {

        sql.executeInsert('INSERT INTO empresas ' +
                '(nome, email, cnpj, pais, cep, descricao, senha) ' +
                "VALUES ('${empresa.nome}', '${empresa.email}', '${empresa.cnpj}', " +
                "'${empresa.pais}', '${empresa.cep}', '${empresa.descricao}', '${empresa.senha}')")
    }

    List<Empresa> listaComTodasEmpresas() {

        List<Empresa> result = new ArrayList<>()
        sql.eachRow('SELECT * FROM empresas') { rs ->
            Empresa empresa = new Empresa(rs.getString('nome').trim(), rs.getString('email').trim(), rs.getString('cnpj'),
                    rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'), rs.getString('senha'))
            result.add(empresa)
            }
        return result
    }

    void remove(String cnpj) {

        validaEmpresas(pegar(cnpj))
        sql.execute("DELETE FROM empresas WHERE cnpj = ${cnpj}")
    }

    Empresa pegar(String cnpj) {

        Empresa empresa = null
        sql.eachRow("SELECT * FROM empresas WHERE cnpj = ${cnpj}") { rs ->
            empresa = new Empresa(rs.getString('nome').trim(), rs.getString('email').trim(), rs.getString('cnpj'),
                    rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'), rs.getString('senha'))
        }
        validaEmpresas(empresa)
        return empresa
    }

    void atualiza(Empresa empresa) {

        sql.executeUpdate('UPDATE empresas ' +
                "SET nome = '${empresa.nome}', email = '${empresa.email}', cnpj = '${empresa.cnpj}', pais = '${empresa.pais}', " +
                "cep = '${empresa.cep}', descricao = '${empresa.descricao}', senha = '${empresa.senha}' WHERE cnpj = '${empresa.cnpj}'")
    }

    int pegaId(String cnpj) {

        int idEmpresa = 0
        sql.eachRow("SELECT id FROM empresas WHERE cnpj = ${cnpj}") { rs ->
            idEmpresa = rs.getInt('id')
        }
        return idEmpresa
    }
}
