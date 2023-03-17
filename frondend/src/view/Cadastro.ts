import Candidato from "../model/candidato.entity";
import PessoasView from "./pessoas.view";
import {ValidaCandidato} from "../util/validaCandidato";
import CandidatoController from "../controller/candidato.controller";

function mostrarEmpresa() {
    document.getElementById('idAddH1')!.innerText = 'Cadastrar Empresa'
    document.getElementById('idCpf')!.style.display = 'none'
    document.getElementById('idLabelCpf')!.style.display = 'none'
    document.getElementById('idIdade')!.style.display = 'none'
    document.getElementById('idLabelIdade')!.style.display = 'none'
    document.getElementById('idCnpj')!.style.display = 'block'
    document.getElementById('idLabelCnpj')!.style.display = 'block'
}

function mostrarCandidato() {
    document.getElementById('idAddH1')!.innerText = 'Cadastrar Candidato'
    document.getElementById('idCpf')!.style.display = 'block'
    document.getElementById('idLabelCpf')!.style.display = 'block'
    document.getElementById('idIdade')!.style.display = 'block'
    document.getElementById('idLabelIdade')!.style.display = 'block'
    document.getElementById('idCnpj')!.style.display = 'none'
    document.getElementById('idLabelCnpj')!.style.display = 'none'
}

function radioTipoPessoa(radio: any) {
    if (radio.value == 'Candidato') {
        mostrarCandidato()
    } else {
        mostrarEmpresa()
    }
}

function adicionaCandidato() {
    const candidato: Candidato = PessoasView.addPessoa() as Candidato
    if (new ValidaCandidato().validacao(candidato)) {
        CandidatoController.enviarCandidato(candidato).then()
        alert('Candidato cadastrado com sucesso!')
        console.log(candidato)
        // window.location.href = '/index.html'
    }
}

export { radioTipoPessoa, adicionaCandidato }