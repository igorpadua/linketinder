import Pessoa from "./pessoa.entity";

export default class Empresa extends Pessoa {
    cnpj: string

    constructor(nome: string, email: string, cnpj: string, desc: string, pais: string, senha: string,
                cep: string) {

        super(nome, email, desc, pais, senha, cep)
        this.cnpj = cnpj
    }
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