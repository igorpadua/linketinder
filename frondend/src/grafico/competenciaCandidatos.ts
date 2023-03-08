import Chart from "chart.js/auto";
import Pessoa from "../pessoa/entities/pessoa.entity";

function contarCompetencia(pessoas: Pessoa[], competencia: string) {
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

export default function desenharCompetencia(pessoas: Pessoa[]) {
    let python = contarCompetencia(pessoas, 'python')
    let java = contarCompetencia(pessoas, 'java')
    let javascript = contarCompetencia(pessoas, 'javascript')
    let c = contarCompetencia(pessoas, 'c')
    let cplusplus = contarCompetencia(pessoas, 'c++')
    let angular = contarCompetencia(pessoas, 'angular')
    let html = contarCompetencia(pessoas, 'html')
    let nodejs = contarCompetencia(pessoas, 'node')
    let spring = contarCompetencia(pessoas, 'springFramework')

    const canvas = <HTMLCanvasElement>document.getElementById('competenciaCandidatos')
    const ctx = canvas.getContext('2d')!

    const myChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['Python', 'Java', 'JavaScript', 'C', 'C++', 'Angular', 'HTML', 'NodeJS', 'Spring'],
            datasets: [{
                label: 'Quantidade de candidatos por competência',
                data: [python, java, javascript, c, cplusplus, angular, html, nodejs, spring],
                borderWidth: 1
            }]
        },
    })
}