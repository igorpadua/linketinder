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

}
