import Pessoa from "../pessoa/entities/pessoa.entity";

export default function contarCompetencia(pessoas: Pessoa[], competencia: string) {
    let count = 0
    pessoas.forEach((pessoa: Pessoa) => {
        pessoa.competencias.forEach((comp: string) => {
            if (comp === competencia) {
                count++
            }
        })
    })
    return count
}
