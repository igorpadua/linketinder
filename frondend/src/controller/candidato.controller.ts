import Candidato from "../model/candidato.entity";

export default class CandidatoController {
    static async enviarCandidato(candidato: Candidato) {
        const response = await fetch('http://localhost:8080/candidato', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },

            body: JSON.stringify(candidato)
        })
        console.log(JSON.stringify(candidato))
        return response.json()
    }

    static async listarCandidatos() {
        const response = await fetch('http://localhost:8080/candidato', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })

        return response.json()
    }
}