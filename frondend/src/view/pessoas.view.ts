import Pessoa from "../model/pessoa.entity";
import Candidato from "../model/candidato.entity";
import Empresa from "../model/empresa.entity";
export default class PessoasView {

    private static pegaDadosPessoa() : string [] {
        const nome: string = (<HTMLInputElement>document.getElementById("idNome")).value
        const email: string = (<HTMLInputElement>document.getElementById("idEmail")).value
        const descricao: string = (<HTMLInputElement>document.getElementById("idDescricao")).value
        const pais: string = (<HTMLInputElement>document.getElementById("idPais")).value
        const estado: string = (<HTMLInputElement>document.getElementById("idEstado")).value
        const cidade: string = (<HTMLInputElement>document.getElementById("idCidade")).value
        const cep: string = (<HTMLInputElement>document.getElementById("idCep")).value
        return [nome, email, descricao, pais, estado, cidade, cep]
    }

    private static pegaCompetencias() : string [] {
        const competencias: string[] = []
        const competencias_selecionadas: NodeListOf<HTMLInputElement> = document.querySelectorAll('input[name="competencias"]:checked')

        competencias_selecionadas.forEach((competencia) => {
            competencias.push(competencia.value)
        })

        return competencias
    }

    static addPessoa() {
        const radio_selecionado: string = (<HTMLInputElement>document.querySelector('input[name="tipoPessoa"]:checked')).value
        const [nome, email, descricao, pais, estado, cidade, cep] = PessoasView.pegaDadosPessoa()
        const competencias: string[] = PessoasView.pegaCompetencias()

        if (radio_selecionado == "Candidato") {
            const cpf: string = (<HTMLInputElement>document.getElementById("idCpf")).value
            const idade: number = parseInt((<HTMLInputElement>document.getElementById("idIdade")).value)
            return new Candidato(nome, email, cpf, idade, descricao, pais, estado, cidade, cep, competencias)
        }

        const cnpj: string = (<HTMLInputElement>document.getElementById("idCnpj")).value
        return new Empresa(nome, email, cnpj, descricao, pais, estado, cidade, cep, competencias)
    }

    static listarPessoas(pessoas: Pessoa[]) {
        let lista_pessoa = ''
        if ((pessoas.at(0) as Candidato).cpf != undefined) {
            lista_pessoa = "<th>Idade</th>"
        }
        lista_pessoa += "<th>Descrição</th><th>País</th><th>Estado</th><th>Cidade</th><th>Competências</th></tr>"
        pessoas.forEach((pessoa) => {
            lista_pessoa += '<tr>'
            if ((pessoa as Candidato).idade != undefined) {
                lista_pessoa += `<td>${(pessoa as Candidato).idade}</td>`
            }
            lista_pessoa +=     `<td>${pessoa.desc}</td>`
            lista_pessoa +=     `<td>${pessoa.pais}</td>`
            lista_pessoa +=     `<td>${pessoa.estado}</td>`
            lista_pessoa +=     `<td>${pessoa.cidade}</td>`
            lista_pessoa +=     `<td>${pessoa.competencias}</td>`
            lista_pessoa += "</tr>"
        })
        if ((pessoas.at(0) as Candidato).cpf != undefined) {
            document.getElementById('listarCandidatos')!.innerHTML = lista_pessoa
        } else {
            document.getElementById('listarEmpresas')!.innerHTML = lista_pessoa
        }
    }
}

