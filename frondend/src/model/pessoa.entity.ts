export default class Pessoa {
    nome: string
    senha: string
    email: string
    desc: string
    pais: string
    cep: string


    constructor(nome: string, email: string, desc: string, pais: string, senha: string, cep: string) {
        this.nome = nome
        this.senha = senha
        this.email = email
        this.desc = desc
        this.pais = pais
        this.cep = cep
    }
}