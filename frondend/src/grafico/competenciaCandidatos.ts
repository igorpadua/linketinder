import Chart from "chart.js/auto";
import Pessoa from "../pessoa/entities/pessoa.entity";

export default function desenharCompetencia(pessoas: Pessoa[]) {
    let python = 0
    let java = 0
    let javascript = 0
    let c = 0
    let cplusplus = 0
    let angular = 0
    let html = 0
    let nodejs = 0
    let spring = 0

    pessoas.forEach((pessoa: Pessoa) => {
        pessoa.competencias.forEach((competencia: string) => {
            switch (competencia) {
                case "python":
                    python++
                    break
                case "java":
                    java++
                    break
                case "javascript":
                    javascript++
                    break
                case "c":
                    c++
                    break
                case "c++":
                    cplusplus++
                    break
                case "angular":
                    angular++
                    break
                case "html":
                    html++
                    break
                case "node":
                    nodejs++
                    break
                case "springFramework":
                    spring++
                    break
            }
        })
    })

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