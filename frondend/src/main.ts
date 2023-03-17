import PessoasView from "./view/pessoas.view";
import Candidato from "./model/candidato.entity";
import Empresa from "./model/empresa.entity";
import graficoCompetencia from "./view/graficoCompetencia";
import {adicionaCandidato, radioTipoPessoa} from "./view/Cadastro";
import ValidaEmpresa from "./util/validaEmpresa";
import {ValidaCandidato} from "./util/validaCandidato";
import CandidatoController from "./controller/candidato.controller";
import CandidatoService from "./service/candidato.service";

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
            /*
            const empresa: Empresa = PessoasView.addPessoa() as Empresa
            if (new ValidaEmpresa().validacao(empresa)) {
                empresas.push(empresa)
                localStorage.setItem('empresas', JSON.stringify(empresas))
                alert('Empresa cadastrada com sucesso!')
                window.location.href = '/index.html'
            }
             */
        }
    }
}

if (window.location.pathname == '/lista_empresa.html') {
    //PessoasView.listarPessoas(empresas)
}

if (window.location.pathname == '/lista_candidato.html') {
    CandidatoController.listarCandidatos().then((candidatos) => {
        candidatos = CandidatoService.transformarCandidatos(candidatos)
        PessoasView.listarPessoas(candidatos)
        graficoCompetencia(candidatos)
    })
}