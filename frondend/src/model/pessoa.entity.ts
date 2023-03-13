export default class Pessoa {
    nome: string
    email: string
    desc: string
    pais: string
    estado: string
    cidade: string
    cep: string
    competencias: string[]


    constructor(nome: string, email: string, desc: string, pais: string, estado: string, cidade: string, cep: string, competencias: string[]) {
        this.nome = nome
        this.email = email
        this.desc = desc
        this.pais = pais
        this.estado = estado
        this.cidade = cidade
        this.cep = cep
        this.competencias = competencias
    }
}