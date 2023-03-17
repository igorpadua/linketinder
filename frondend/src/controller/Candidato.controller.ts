import Candidato from "../model/candidato.entity";

export default class CandidatoController {
    // Envia os dados do candidato para o backend
    static async enviarCandidato(candidato: Candidato) {
        const nascimento = candidato.nascimento.getDate() + '/' + (candidato.nascimento.getMonth() + 1) + '/' + candidato.nascimento.getFullYear()
        const dados = {nome: candidato.nome, sobrenome: candidato.sobrenome,cpf: candidato.cpf, descricao: candidato.desc,
        email: candidato.email,  pais: candidato.pais, cep: candidato.cep, senha: candidato.senha,
        competencias: candidato.competencias.toString(), nascimento: nascimento}
        const response = await fetch('http://localhost:8080/candidato', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },

            body: JSON.stringify(dados)
        })
        console.log(JSON.stringify(dados))
        return response.json()
    }
}