import Pessoa from "./pessoa.entity";

export default class Empresa extends Pessoa {
    cnpj: string

    constructor(nome: string, email: string, cnpj: string, desc: string, pais: string, estado: string, cidade: string, cep: string, competencias: string[]) {
        super(nome, email, desc, pais, estado, cidade, cep, competencias)
        this.cnpj = cnpj
    }

}