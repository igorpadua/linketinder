package main.groovy.dto

import groovy.sql.Sql
import main.groovy.entity.Empresa

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
        sql.execute('DELETE FROM empresas WHERE cnpj = ?', [cnpj])
        sql.close()
    }

    static Empresa getEmpresa(String cnpj) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        Empresa empresa = null
        sql.eachRow('SELECT * FROM empresas WHERE cnpj = ?', [cnpj]) { rs ->
            empresa = new Empresa(rs.getString('nome').trim(), rs.getString('email').trim(), rs.getString('cnpj'),
                    rs.getString('pais'), rs.getString('cep'), rs.getString('descricao'), rs.getString('senha'))
        }
        sql.close()
        return empresa
    }

    static void atualizarEmpresa(Empresa empresa) {
        Sql sql = Sql.newInstance(url, user, password, drive)
        sql.executeUpdate('UPDATE empresas ' +
                'SET nome = ?, email = ?, cnpj = ?, pais = ?, cep = ?, descricao = ?, senha = ? WHERE cnpj = ?',
                [empresa.nome, empresa.email, empresa.cnpj, empresa.pais, empresa.cep, empresa.desc, empresa.senha, empresa.cnpj])
        sql.close()
    }
}
