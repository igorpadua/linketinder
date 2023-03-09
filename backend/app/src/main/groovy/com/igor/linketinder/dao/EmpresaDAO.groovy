package com.igor.linketinder.dao

import groovy.sql.Sql
import groovy.transform.TypeChecked
import com.igor.linketinder.entity.Empresa

@TypeChecked
class EmpresaDAO {
    static final url = 'jdbc:postgresql://localhost/liketinder'
    static final user= 'postgres'
    static final password= '123456'
    static final drive= "org.postgresql.Driver"

    private static validaEmpresas(Empresa empresa)  {
        if (empresa == null) {
            throw new IllegalArgumentException("Não existe empresa com esse CNPJ")
        }
    }

    static void adicionar(Empresa empresa) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeInsert('INSERT INTO empresas ' +
                '(nome, email, cnpj, pais, cep, descricao, senha) ' +
                "VALUES ('${empresa.nome}', '${empresa.email}', '${empresa.cnpj}', " +
                "'${empresa.pais}', '${empresa.cep}', '${empresa.descricao}', '${empresa.senha}')")
        sql.close()
    }

    static List<Empresa> listaComTodasEmpresas() {
        Sql sql = Sql.newInstance(url, user, password, drive)
        List<Empresa> result = new ArrayList<>()
        sql.eachRow('SELECT * FROM empresas') { rs ->
            Empresa empresa = new Empresa(rs.getString('nome').trim(), rs.getString('email').trim(), rs.getString('cnpj'),
                    rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'), rs.getString('senha'))
            result.add(empresa)
            }
        sql.close()
        return result
    }

    static void remove(String cnpj) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        validaEmpresas(pega(cnpj))
        sql.execute("DELETE FROM empresas WHERE cnpj = ${cnpj}")
        sql.close()
    }

    static Empresa pega(String cnpj) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        Empresa empresa = null
        sql.eachRow("SELECT * FROM empresas WHERE cnpj = ${cnpj}") { rs ->
            empresa = new Empresa(rs.getString('nome').trim(), rs.getString('email').trim(), rs.getString('cnpj'),
                    rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'), rs.getString('senha'))
        }
        sql.close()
        validaEmpresas(empresa)
        return empresa
    }

    static void atualiza(Empresa empresa) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeUpdate('UPDATE empresas ' +
                "SET nome = '${empresa.nome}', email = '${empresa.email}', cnpj = '${empresa.cnpj}', pais = '${empresa.pais}', " +
                "cep = '${empresa.cep}', descricao = '${empresa.descricao}', senha = '${empresa.senha}' WHERE cnpj = '${empresa.cnpj}'")
        sql.close()
    }

    static int pegaId(String cnpj) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        int idEmpresa = 0
        sql.eachRow("SELECT id FROM empresas WHERE cnpj = ${cnpj}") { rs ->
            idEmpresa = rs.getInt('id')
        }
        sql.close()
        return idEmpresa
    }
}
