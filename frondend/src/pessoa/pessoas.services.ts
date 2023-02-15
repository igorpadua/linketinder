import Pessoa from "./entities/pessoa.entity";
import Candidato from "./entities/candidato.entity";
import Empresa from "./entities/empresa.entity";
import Chart from 'chart.js/auto';

export default class PessoasServices {

    static addPessoa(): Pessoa {
        let pessoa: Pessoa
        // Radio button
        const radio_selecionado: string = (<HTMLInputElement>document.querySelector('input[name="tipoPessoa"]:checked')).value

        const nome: string = (<HTMLInputElement>document.getElementById("idNome")).value
        const email: string = (<HTMLInputElement>document.getElementById("idEmail")).value
        const descricao: string = (<HTMLInputElement>document.getElementById("idDescricao")).value
        const pais: string = (<HTMLInputElement>document.getElementById("idPais")).value
        const estado: string = (<HTMLInputElement>document.getElementById("idEstado")).value
        const cidade: string = (<HTMLInputElement>document.getElementById("idCidade")).value
        const cep: string = (<HTMLInputElement>document.getElementById("idCep")).value
        // Competencias checkbox
        const competencias: string[] = []
        const competencias_selecionadas: NodeListOf<HTMLInputElement> = document.querySelectorAll('input[name="competencias"]:checked')

        competencias_selecionadas.forEach((competencia) => {
            competencias.push(competencia.value)
        })

        if (radio_selecionado == "Candidato") {
            const cpf: string = (<HTMLInputElement>document.getElementById("idCpf")).value
            const idade: number = parseInt((<HTMLInputElement>document.getElementById("idIdade")).value)
            return new Candidato(nome, email, cpf, idade, descricao, pais, estado, cidade, cep, competencias)
        }

        const cnpj: string = (<HTMLInputElement>document.getElementById("idCnpj")).value
        return new Empresa(nome, email, cnpj, descricao, pais, estado, cidade, cep, competencias)
    }

    static getCandidatos() {
        let lista_candidatos = "<th>Idade</th><th>Descrição</th><th>País</th><th>Estado</th><th>Cidade</th><th>Competências</th></tr>"
        const candidatos: Candidato[] = JSON.parse(localStorage.getItem('candidatos')!)
        candidatos.forEach((candidato) => {
            lista_candidatos += `<tr>
                                    <td>${candidato.idade}</td>
                                    <td>${candidato.desc}</td>
                                    <td>${candidato.pais}</td>
                                    <td>${candidato.estado}</td>
                                    <td>${candidato.cidade}</td>
                                    <td>${candidato.competencias}</td>
                              </tr>`
        })
        document.getElementById('listarCandidatos')!.innerHTML = lista_candidatos
    }

    static getEmpresas() {
        let lista_empresas = "<tr><th>Descrição</th><th>País</th><th>Estado</th><th>Cidade</th><th>CEP</th><th>Competências</th></tr>"
        const empresas: Empresa[] = JSON.parse(localStorage.getItem('empresas')!)
        empresas.forEach((empresa) => {
            lista_empresas += `<tr>
                                    <td>${empresa.desc}</td>
                                    <td>${empresa.pais}</td>
                                    <td>${empresa.estado}</td>
                                    <td>${empresa.cidade}</td>
                                    <td>${empresa.cep}</td>
                                    <td>${empresa.competencias}</td>
                              </tr>`
        })
        document.getElementById('listarEmpresas')!.innerHTML = lista_empresas
    }

    static desenharCompetenciaCandidatos() {
        const candidatos = JSON.parse(localStorage.getItem('candidatos')!)
        let python = 0
        let java = 0
        let javascript = 0
        let c = 0
        let cplusplus = 0
        let angular = 0
        let html = 0
        let nodejs = 0
        let spring = 0

        candidatos.forEach((candidato: Candidato) => {
            candidato.competencias.forEach((competencia: string) => {
                switch (competencia) {
                    case "python":
                        python++
                        break
                    case "java":
                        java++
                        break
                    case "javaScript":
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
                    case "nodejs":
                        nodejs++
                        break
                    case "spring":
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
}

