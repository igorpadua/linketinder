import PessoasView from "./view/pessoas.view";
import Candidato from "./model/candidato.entity";
import graficoCompetencia from "./view/graficoCompetencia";
import {adicionaCandidato, adicionaEmpresa, radioTipoPessoa} from "./view/Cadastro";
import CandidatoController from "./controller/candidato.controller";
import EmpresaController from "./controller/empresa.controller";
import Empresa from "./model/empresa.entity";

if (window.location.pathname == '/cadastro.html') {

    document.getElementById('idAddH1')!.innerText = 'Cadastrar Candidato'
    document.getElementById('idCnpj')!.style.display = 'none'
    document.getElementById('idLabelCnpj')!.style.display = 'none'

    document.getElementsByName('tipoPessoa').forEach((radio: any) => {
        radio.onclick = () => { radioTipoPessoa(radio) }
    })

    document.getElementById('addCadastro')!.onclick = () => {
        const radio_selecionado: string = (<HTMLInputElement>document.querySelector('input[name="tipoPessoa"]:checked')).value
        if (radio_selecionado == 'Candidato') {
            adicionaCandidato()
        } else {
            adicionaEmpresa()
        }
    }
}

if (window.location.pathname == '/lista_empresa.html') {
    EmpresaController.listarEmpresas().then((empresas) => {
        empresas = Empresa.transformarEmpresas(empresas)
        console.log(empresas)
        PessoasView.listarPessoas(empresas)
    })
}

if (window.location.pathname == '/lista_candidato.html') {
    CandidatoController.listarCandidatos().then((candidatos) => {
        candidatos = Candidato.transformarCandidatos(candidatos)
        PessoasView.listarPessoas(candidatos)
        graficoCompetencia(candidatos)
    })
}