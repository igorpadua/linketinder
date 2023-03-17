import Candidato from "../model/candidato.entity";

export default class CandidatoService {

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
            const competencias: string[] = candidato.competencia.competencias
            const nascimento: Date = new Date(candidato.nascimento)
            candidatos.push(new Candidato(nome, email, cpf, descricao, pais, senha, nascimento, cep, sobrenome, competencias))
        })

        return candidatos
    }
}