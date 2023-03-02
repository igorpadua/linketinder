import Pessoa from "../pessoa/entities/pessoa.entity";
import Candidato from "../pessoa/entities/candidato.entity";
import Empresa from "../pessoa/entities/empresa.entity";

export default function validaPessoa(pessoa: Pessoa) {

    if (!(pessoa.nome && pessoa.email && pessoa.desc && pessoa.pais && pessoa.estado && pessoa.cidade && pessoa.cep && pessoa.competencias.toString())) {
        alert("Preencha todos os campos!")
        return false
    }

    if (pessoa instanceof Candidato) {
        if (!(pessoa as Candidato).cpf && !(pessoa as Candidato).idade) {
            alert("Preencha todos os campos!")
            return false
        }
    }

    if (pessoa instanceof Empresa) {
        if (!(pessoa as Empresa).cnpj) {
            alert("Preencha todos os campos!")
            return false
        }
    }

    const genericRegex: RegExp = /^[a-zA-Z ]{2,30}$/
    const emailRegex: RegExp = /^\S+@\w+\.\w{2,6}(\.\w{2})?$/
    const descRegex: RegExp = /^(?=.*[A-Za-z])([0-9])*.{3,100}$/
    const cepRegex: RegExp = /^\d{5}-\d{3}$/
    const cpfRegex: RegExp = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/
    const cnpjRegex: RegExp = /^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/
    const idadeRegex: RegExp = /^\d{2}$/

    if (!genericRegex.test(pessoa.nome)) {
        alert("Nome inválido!")
        return false
    }
    if (!emailRegex.test(pessoa.email)) {
        alert("Email inválido!")
        return false
    }
    if (!descRegex.test(pessoa.desc)) {
        alert("Descrição inválida!")
        return false
    }
    if (pessoa instanceof Candidato) {
        if (!cpfRegex.test((pessoa as Candidato).cpf)) {
            alert("CPF inválido!")
            return false
        }
        if (!idadeRegex.test((pessoa as Candidato).idade.toString())) {
            alert("Idade inválida!")
            return false
        }
    }
    if (pessoa instanceof Empresa) {
        if (!cnpjRegex.test((pessoa as Empresa).cnpj)) {
            alert("CNPJ inválido!")
            return false
        }
    }
    if (!genericRegex.test(pessoa.pais)) {
        alert("País inválido!")
        return false
    }
    if (!genericRegex.test(pessoa.estado)) {
        alert("Estado inválido!")
        return false
    }
    if (!genericRegex.test(pessoa.cidade)) {
        alert("Cidade inválida!")
        return false
    }
    if (!cepRegex.test(pessoa.cep)) {
        alert("CEP inválido!")
        return false
    }

    return true
}