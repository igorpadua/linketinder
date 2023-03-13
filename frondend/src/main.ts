import PessoasView from "./view/pessoas.view";
import Candidato from "./model/candidato.entity";
import Empresa from "./model/empresa.entity";
import graficoCompetencia from "./view/graficoCompetencia";
import {mostrarCandidato, mostrarEmpresa} from "./view/Cadastro";
import ValidaEmpresa from "./util/validaEmpresa";
import {ValidaCandidato} from "./util/validaCandidato";

let candidatos: Candidato[] = []
let empresas: Empresa[] = []

if (localStorage.getItem('candidatos')) {
    candidatos = JSON.parse(localStorage.getItem('candidatos')!)
}

if (localStorage.getItem('empresas')) {
    empresas = JSON.parse(localStorage.getItem('empresas')!)
}

if (window.location.pathname == '/cadastro.html') {

    document.getElementById('idAddH1')!.innerText = 'Cadastrar Candidato'
    document.getElementById('idCnpj')!.style.display = 'none'
    document.getElementById('idLabelCnpj')!.style.display = 'none'

    document.getElementsByName('tipoPessoa').forEach((radio: any) => {
        radio.onclick = () => {
            if (radio.value == 'Candidato') {
                mostrarCandidato()
            } else {
                mostrarEmpresa()
            }
        }
    })

    document.getElementById('addCadastro')!.onclick = () => {
        const radio_selecionado: string = (<HTMLInputElement>document.querySelector('input[name="tipoPessoa"]:checked')).value
        if (radio_selecionado == 'Candidato') {
            const candidato: Candidato = PessoasView.addPessoa() as Candidato
            if (new ValidaCandidato().validacao(candidato)) {
                candidatos.push(candidato)
                localStorage.setItem('candidatos', JSON.stringify(candidatos))
                alert('Candidato cadastrado com sucesso!')
                window.location.href = '/index.html'
            }
        } else {
            const empresa: Empresa = PessoasView.addPessoa() as Empresa
            if (new ValidaEmpresa().validacao(empresa)) {
                empresas.push(empresa)
                localStorage.setItem('empresas', JSON.stringify(empresas))
                alert('Empresa cadastrada com sucesso!')
                window.location.href = '/index.html'
            }
        }
    }
}

if (window.location.pathname == '/lista_empresa.html') {
    PessoasView.listarPessoas(empresas)
}

if (window.location.pathname == '/lista_candidato.html') {
    PessoasView.listarPessoas(candidatos)
    graficoCompetencia(candidatos)
}