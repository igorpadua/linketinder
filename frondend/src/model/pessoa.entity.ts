export default class Pessoa {
    nome: string
    senha: string
    email: string
    descricao: string
    pais: string
    cep: string


    constructor(nome: string, email: string, desc: string, pais: string, senha: string, cep: string) {
        this.nome = nome
        this.senha = senha
        this.email = email
        this.descricao = desc
        this.pais = pais
        this.cep = cep
    }
}