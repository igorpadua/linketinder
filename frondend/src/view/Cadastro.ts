import Candidato from "../model/candidato.entity";
import PessoasView from "./pessoas.view";
import {ValidaCandidato} from "../util/validaCandidato";
import CandidatoController from "../controller/candidato.controller";
import ValidaEmpresa from "../util/validaEmpresa";
import Empresa from "../model/empresa.entity";
import EmpresaController from "../controller/empresa.controller";

function mostrarEmpresa() {
    document.getElementById('idAddH1')!.innerText = 'Cadastrar Empresa'
    document.getElementById('idCpf')!.style.display = 'none'
    document.getElementById('idLabelCpf')!.style.display = 'none'
    document.getElementById('idNascimento')!.style.display = 'none'
    document.getElementById('idLabelNascimento')!.style.display = 'none'
    document.getElementById('idSobrenome')!.style.display = 'none'
    document.getElementById('idLabelSobrenome')!.style.display = 'none'
    document.getElementById('idCompetencia')!.style.display = 'none'
    document.getElementById('idCnpj')!.style.display = 'block'
    document.getElementById('idLabelCnpj')!.style.display = 'block'
}

function mostrarCandidato() {
    document.getElementById('idAddH1')!.innerText = 'Cadastrar Candidato'
    document.getElementById('idCpf')!.style.display = 'block'
    document.getElementById('idLabelCpf')!.style.display = 'block'
    document.getElementById('idNascimento')!.style.display = 'block'
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
        try {
            CandidatoController.enviarCandidato(candidato).then()
            alert('Candidato cadastrado com sucesso!')
            window.location.href = '/index.html'
        } catch (e) {
            alert('Erro ao cadastrar candidato!')
            console.log(e)
        }
    }
}

function adicionaEmpresa() {
    const empresa: Empresa = PessoasView.addPessoa() as Empresa
    if (new ValidaEmpresa().validacao(empresa)) {
        EmpresaController.enviarEmpresa(empresa).then()
        alert('Empresa cadastrada com sucesso!')
        window.location.href = '/index.html'
    }
}

export { radioTipoPessoa, adicionaCandidato, adicionaEmpresa }