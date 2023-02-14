import Pessoa from "./pessoa.entity";

export default class Candidato extends Pessoa {
    cpf: string
    idade: number

    constructor(nome: string, email: string, cpf: string, idade: number, desc: string, pais: string, estado: string, cidade: string, cep: string, competencias: string[]) {
        super(nome, email, desc, pais, estado, cidade, cep, competencias)
        this.cpf = cpf
        this.idade = idade
    }
}