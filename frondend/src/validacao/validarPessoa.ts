import Pessoa from "../pessoa/entities/pessoa.entity";

export default class ValidaPessoa {
    static nomeRegex: RegExp = /^[a-zA-Z ]{2,30}$/
    static emailRegex: RegExp = /^\S+@\w+\.\w{2,6}(\.\w{2})?$/
    static descRegex: RegExp = /^(?=.*[A-Za-z])([0-9])*.{3,100}$/
    static cepRegex: RegExp = /^\d{5}-\d{3}$/

    protected static dadosForamPreenchidos(pessoa: Pessoa) : boolean {
        return !(pessoa.nome && pessoa.email && pessoa.desc && pessoa.pais && pessoa.estado && pessoa.cidade && pessoa.cep)
    }
    protected static dadosPrincipais(pessoa: Pessoa) : boolean {
        if (!this.nomeRegex.test(pessoa.nome)) {
            alert("Nome inválido!")
            return false
        }
        if (!this.emailRegex.test(pessoa.email)) {
            alert("Email inválido!")
            return false
        }
        if (!this.descRegex.test(pessoa.desc)) {
            alert("Descrição inválida!")
            return false
        }

        if (!this.nomeRegex.test(pessoa.pais)) {
            alert("País inválido!")
            return false
        }
        if (!this.nomeRegex.test(pessoa.estado)) {
            alert("Estado inválido!")
            return false
        }
        if (!this.nomeRegex.test(pessoa.cidade)) {
            alert("Cidade inválida!")
            return false
        }
        if (!this.cepRegex.test(pessoa.cep)) {
            alert("CEP inválido!")
            return false
        }
        return true
    }


}

