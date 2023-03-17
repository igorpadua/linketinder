import Pessoa from "../model/pessoa.entity";
import Candidato from "../model/candidato.entity";

export default function contarCompetencia(candidatos: Candidato[], competencia: string) {
    let count = 0
    candidatos.forEach((candidato: Candidato) => {
        candidato.competencias.forEach((comp: string) => {
            if (comp === competencia) {
                count++
            }
        })
    })
    return count
}
