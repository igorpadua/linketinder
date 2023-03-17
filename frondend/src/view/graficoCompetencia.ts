import Chart from "chart.js/auto";
import contarCompetencia from "../util/contaCompetencia";
import Candidato from "../model/candidato.entity";

export default function desenharCompetencia(candidatos: Candidato[]) {
    const python = contarCompetencia(candidatos, 'python')
    const java = contarCompetencia(candidatos, 'java')
    const javascript = contarCompetencia(candidatos, 'javascript')
    const c = contarCompetencia(candidatos, 'c')
    const cplusplus = contarCompetencia(candidatos, 'c++')
    const angular = contarCompetencia(candidatos, 'angular')
    const html = contarCompetencia(candidatos, 'html')
    const nodejs = contarCompetencia(candidatos, 'node')
    const spring = contarCompetencia(candidatos, 'springFramework')

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