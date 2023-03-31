import Pessoa from "./pessoa.entity";
import Competencia from "./competencia.entity";

export default class Candidato extends Pessoa {
    sobrenome: string
    cpf: string
    nascimento: Date
    competencias: Competencia[]

    constructor(nome: string, email: string, cpf: string, desc: string, pais: string,
                senha: string, nasicmento: Date, cep: string, sobrenome: string,competencias: Competencia[]) {
        super(nome, email, desc, pais, senha, cep)
        this.competencias = competencias
        this.sobrenome = sobrenome
        this.cpf = cpf
        this.nascimento = nasicmento
    }

    static transformarCandidatos(candidato: Object[]): Candidato[] {
        let candidatos: Candidato[] = []
        candidato.forEach((candidato: any) => {
            const nome: string = candidato.nome
            const sobrenome: string = candidato.sobrenome
            const cpf: string = candidato.cpf
            const descricao: string = candidato.descricao
            const email: string = candidato.email
            const pais: string = candidato.pais
            const cep: string = candidato.cep
            const senha: string = candidato.senha

            let competencias: Competencia[] = []

            candidato.competencias.forEach((competencia: any) => {
                competencias.push(new Competencia(competencia.competencia))
            })

            const nascimento: Date = new Date(candidato.nascimento)
            candidatos.push(new Candidato(nome, email, cpf, descricao, pais, senha, nascimento, cep, sobrenome, competencias))
        })

        return candidatos
    }
}