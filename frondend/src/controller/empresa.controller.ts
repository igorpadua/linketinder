import Empresa from "../model/empresa.entity";

export default class EmpresaController {

    static async enviarEmpresa(empresa: Empresa) {
        const response = await fetch('http://localhost:8080/empresa', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },

            body: JSON.stringify(empresa)
        })
        console.log(JSON.stringify(empresa))
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