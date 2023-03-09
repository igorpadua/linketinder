package com.igor.linketinder.dao

import groovy.sql.Sql
import groovy.transform.TypeChecked
import com.igor.linketinder.entity.Empresa

@TypeChecked
class EmpresaDAO extends ConectarBanco {

    private static validaEmpresas(Empresa empresa)  {
        if (empresa == null) {
            throw new IllegalArgumentException("Não existe empresa com esse CNPJ")
        }
    }

    static void adicionar(Empresa empresa) {
        Sql sql = conectar()
        sql.executeInsert('INSERT INTO empresas ' +
                '(nome, email, cnpj, pais, cep, descricao, senha) ' +
                "VALUES ('${empresa.nome}', '${empresa.email}', '${empresa.cnpj}', " +
                "'${empresa.pais}', '${empresa.cep}', '${empresa.descricao}', '${empresa.senha}')")
        desconectar(sql)
    }

    static List<Empresa> listaComTodasEmpresas() {
        Sql sql = conectar()
        List<Empresa> result = new ArrayList<>()
        sql.eachRow('SELECT * FROM empresas') { rs ->
            Empresa empresa = new Empresa(rs.getString('nome').trim(), rs.getString('email').trim(), rs.getString('cnpj'),
                    rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'), rs.getString('senha'))
            result.add(empresa)
            }
        desconectar(sql)
        return result
    }

    static void remove(String cnpj) {
        Sql sql = conectar()
        validaEmpresas(pega(cnpj))
        sql.execute("DELETE FROM empresas WHERE cnpj = ${cnpj}")
        desconectar(sql)
    }

    static Empresa pega(String cnpj) {
        Sql sql = conectar()
        Empresa empresa = null
        sql.eachRow("SELECT * FROM empresas WHERE cnpj = ${cnpj}") { rs ->
            empresa = new Empresa(rs.getString('nome').trim(), rs.getString('email').trim(), rs.getString('cnpj'),
                    rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'), rs.getString('senha'))
        }
        desconectar(sql)
        validaEmpresas(empresa)
        return empresa
    }

    static void atualiza(Empresa empresa) {
        Sql sql = conectar()
        sql.executeUpdate('UPDATE empresas ' +
                "SET nome = '${empresa.nome}', email = '${empresa.email}', cnpj = '${empresa.cnpj}', pais = '${empresa.pais}', " +
                "cep = '${empresa.cep}', descricao = '${empresa.descricao}', senha = '${empresa.senha}' WHERE cnpj = '${empresa.cnpj}'")
        desconectar(sql)
    }

    static int pegaId(String cnpj) {
        Sql sql = conectar()
        int idEmpresa = 0
        sql.eachRow("SELECT id FROM empresas WHERE cnpj = ${cnpj}") { rs ->
            idEmpresa = rs.getInt('id')
        }
        desconectar(sql)
        return idEmpresa
    }
}
