import Pessoa from "../model/pessoa.entity";
import Candidato from "../model/candidato.entity";
import Empresa from "../model/empresa.entity";
export default class PessoasView {

    private static pegaDadosPessoa() : string [] {
        const nome: string = (<HTMLInputElement>document.getElementById("idNome")).value
        const email: string = (<HTMLInputElement>document.getElementById("idEmail")).value
        const descricao: string = (<HTMLInputElement>document.getElementById("idDescricao")).value
        const pais: string = (<HTMLInputElement>document.getElementById("idPais")).value
        const cep: string = (<HTMLInputElement>document.getElementById("idCep")).value
        const senha: string = (<HTMLInputElement>document.getElementById("idSenha")).value
        return [nome, email, descricao, pais, cep, senha]
    }

    private static pegaDadosCandidato() {
        const cpf: string = (<HTMLInputElement>document.getElementById("idCpf")).value
        const sobrenome: string = (<HTMLInputElement>document.getElementById("idSobrenome")).value
        const nascimento: string = (<HTMLInputElement>document.getElementById("idNascimento")).value
        return [cpf, sobrenome, nascimento]
    }

    private static pegaCompetencias() : string [] {
        const competencias: string[] = []
        const competencias_selecionadas: NodeListOf<HTMLInputElement> = document.querySelectorAll('input[name="competencias"]:checked')

        competencias_selecionadas.forEach((competencia) => {
            competencias.push(competencia.value)
        })

        return competencias
    }

    static addPessoa(): Pessoa {
        const radio_selecionado: string = (<HTMLInputElement>document.querySelector('input[name="tipoPessoa"]:checked')).value
        const [nome, email, descricao, pais, cep, senha] = PessoasView.pegaDadosPessoa()
        const competencias: string[] = PessoasView.pegaCompetencias()

        if (radio_selecionado == "Candidato") {
            const [cpf, sobrenome, nascimento] = PessoasView.pegaDadosCandidato()
            const date_nascimento: Date = new Date(nascimento)
            date_nascimento.setDate(date_nascimento.getDate() + 1)
            return new Candidato(nome, email, cpf, descricao, pais, senha, date_nascimento, cep, sobrenome, competencias)
        }

        const cnpj: string = (<HTMLInputElement>document.getElementById("idCnpj")).value
        return new Empresa(nome, email, cnpj, descricao, pais, senha, cep, competencias)
    }

    static listarPessoas(pessoas: Pessoa[]) {
        let lista_pessoa = ''
        if ((pessoas.at(0) as Candidato).cpf != undefined) {
            lista_pessoa = "<th>Idade</th>"
        }
        lista_pessoa += "<th>Descrição</th><th>País</th><th>Estado</th><th>Cidade</th><th>Competências</th></tr>"
        pessoas.forEach((pessoa) => {
            lista_pessoa += '<tr>'
            if ((pessoa as Candidato).nascimento != undefined) {
                lista_pessoa += `<td>${(pessoa as Candidato).nascimento}</td>`
            }
            lista_pessoa +=     `<td>${pessoa.desc}</td>`
            lista_pessoa +=     `<td>${pessoa.pais}</td>`
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

