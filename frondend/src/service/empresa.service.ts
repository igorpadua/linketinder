import Empresa from "../model/empresa.entity";

export default class EmpresaService {
    static transformarEmpresas(empresa: Object[]): Empresa[] {
        let empresas: Empresa[] = []
        empresa.forEach((empresa: any) => {
            const nome: string = empresa.nome
            const cnpj: string = empresa.cnpj
            const descricao: string = empresa.descricao
            const email: string = empresa.email
            const pais: string = empresa.pais
            const cep: string = empresa.cep
            const senha: string = empresa.senha
            empresas.push(new Empresa(nome, email, cnpj, descricao, pais, senha, cep))
        })
        return empresas
    }
}