import Pessoa from "./pessoa/entities/pessoa.entity";
import PessoasServices from "./pessoa/pessoas.services";
import Candidato from "./pessoa/entities/candidato.entity";
import Empresa from "./pessoa/entities/empresa.entity";

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
                document.getElementById('idAddH1')!.innerText = 'Cadastrar Candidato'
                document.getElementById('idCpf')!.style.display = 'block'
                document.getElementById('idLabelCpf')!.style.display = 'block'
                document.getElementById('idIdade')!.style.display = 'block'
                document.getElementById('idLabelIdade')!.style.display = 'block'
                document.getElementById('idCnpj')!.style.display = 'none'
                document.getElementById('idLabelCnpj')!.style.display = 'none'
            } else {
                document.getElementById('idAddH1')!.innerText = 'Cadastrar Empresa'
                document.getElementById('idCpf')!.style.display = 'none'
                document.getElementById('idLabelCpf')!.style.display = 'none'
                document.getElementById('idIdade')!.style.display = 'none'
                document.getElementById('idLabelIdade')!.style.display = 'none'
                document.getElementById('idCnpj')!.style.display = 'block'
                document.getElementById('idLabelCnpj')!.style.display = 'block'
            }
        }
    })

    document.getElementById('addCadastro')!.onclick = () => {
        const radio_selecionado: string = (<HTMLInputElement>document.querySelector('input[name="tipoPessoa"]:checked')).value
        if (radio_selecionado == 'Candidato') {
            const pessoa: Candidato = PessoasServices.addPessoa() as Candidato
            if (PessoasServices.validarPessoa(pessoa)) {
                candidatos.push(pessoa)
                localStorage.setItem('candidatos', JSON.stringify(candidatos))
                alert('Candidato cadastrado com sucesso!')
                window.location.href = '/index.html'
            }
        } else {
            const pessoa: Empresa = PessoasServices.addPessoa() as Empresa
            if (PessoasServices.validarPessoa(pessoa)){
                empresas.push(pessoa)
                localStorage.setItem('empresas', JSON.stringify(empresas))
                alert('Empresa cadastrada com sucesso!')
                window.location.href = '/index.html'
            }
        }

    }
}

if (window.location.pathname == '/lista_empresa.html') {
    PessoasServices.getEmpresas()
}

if (window.location.pathname == '/lista_candidato.html') {
    PessoasServices.getCandidatos()
    PessoasServices.desenharCompetenciaCandidatos()
}