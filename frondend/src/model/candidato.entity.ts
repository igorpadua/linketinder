import Pessoa from "./pessoa.entity";

export default class Candidato extends Pessoa {
    sobrenome: string
    cpf: string
    nascimento: Date

    constructor(nome: string, email: string, cpf: string, desc: string, pais: string,
                senha: string, nasicmento: Date, cep: string, sobrenome: string,competencias: string[]) {
        super(nome, email, desc, pais, senha, cep, competencias)
        this.sobrenome = sobrenome
        this.cpf = cpf
        this.nascimento = nasicmento
    }
}