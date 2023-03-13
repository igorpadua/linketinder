import Chart from "chart.js/auto";
import Pessoa from "../model/pessoa.entity";
import contarCompetencia from "../util/contaCompetencia";

export default function desenharCompetencia(pessoas: Pessoa[]) {
    const python = contarCompetencia(pessoas, 'python')
    const java = contarCompetencia(pessoas, 'java')
    const javascript = contarCompetencia(pessoas, 'javascript')
    const c = contarCompetencia(pessoas, 'c')
    const cplusplus = contarCompetencia(pessoas, 'c++')
    const angular = contarCompetencia(pessoas, 'angular')
    const html = contarCompetencia(pessoas, 'html')
    const nodejs = contarCompetencia(pessoas, 'node')
    const spring = contarCompetencia(pessoas, 'springFramework')

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