import Candidato from "../model/candidato.entity";
import Competencia from "../model/competencia.entity";

export default function contarCompetencia(candidatos: Candidato[], competencia: string) {
    let count = 0
    candidatos.forEach((candidato: Candidato) => {
        candidato.competencias.forEach((competenciaCandidato: Competencia) => {
            if (competenciaCandidato.competencia === competencia) {
                count++
            }
        })
    })
    return count
}
