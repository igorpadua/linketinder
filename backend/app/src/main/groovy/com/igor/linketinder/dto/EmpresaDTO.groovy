package main.groovy.com.igor.linketinder.dto

import groovy.sql.Sql
import groovy.transform.TypeChecked
import main.groovy.com.igor.linketinder.entity.Empresa

@TypeChecked
class EmpresaDTO {
    static final url = 'jdbc:postgresql://localhost/liketinder'
    static final user= 'postgres'
    static final password= '123456'
    static final drive= "org.postgresql.Driver"

    static void inserirEmpresa(Empresa empresa) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeInsert('INSERT INTO empresas ' +
                '(nome, email, cnpj, pais, cep, descricao, senha) ' +
                "VALUES ('${empresa.nome}', '${empresa.email}', '${empresa.cnpj}', " +
                "'${empresa.pais}', '${empresa.cep}', '${empresa.desc}', '${empresa.senha}')")
        sql.close()
    }

    static List<Empresa> listarEmpresas() {
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

    static void removeEmpresa(String cnpj) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.execute("DELETE FROM empresas WHERE cnpj = ${cnpj}")
        sql.close()
    }

    static Empresa getEmpresa(String cnpj) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        Empresa empresa = null
        sql.eachRow("SELECT * FROM empresas WHERE cnpj = ${cnpj}") { rs ->
            empresa = new Empresa(rs.getString('nome').trim(), rs.getString('email').trim(), rs.getString('cnpj'),
                    rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'), rs.getString('senha'))
        }
        sql.close()
        return empresa
    }

    static void atualizarEmpresa(Empresa empresa) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeUpdate('UPDATE empresas ' +
                "SET nome = '${empresa.nome}', email = '${empresa.email}', cnpj = '${empresa.cnpj}', pais = '${empresa.pais}', " +
                "cep = '${empresa.cep}', descricao = '${empresa.desc}', senha = '${empresa.senha}' WHERE cnpj = '${empresa.cnpj}'")
        sql.close()
    }

    static int getIdEmpresa(String cnpj) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        int id = 0
        sql.eachRow("SELECT id FROM empresas WHERE cnpj = ${cnpj}") { rs ->
            id = rs.getInt('id')
        }
        sql.close()
        return id
    }
}
