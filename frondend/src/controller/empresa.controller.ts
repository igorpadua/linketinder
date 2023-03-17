import Empresa from "../model/empresa.entity";

export default class EmpresaController {

    static async enviarEmpresa(empresa: Empresa) {
        const dados = {nome: empresa.nome, cnpj: empresa.cnpj, descricao: empresa.desc,
        email: empresa.email,  pais: empresa.pais, cep: empresa.cep, senha: empresa.senha}
        const response = await fetch('http://localhost:8080/empresa', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },

            body: JSON.stringify(dados)
        })
        console.log(JSON.stringify(dados))
        return response.json()
    }

    static async listarEmpresas() {
        const response = await fetch('http://localhost:8080/empresa', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })

        return response.json()
    }
}