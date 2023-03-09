import Pessoa from "../pessoa/entities/pessoa.entity";

export default abstract class ValidaPessoa {
    nomeRegex: RegExp = /^[a-zA-Z ]{2,30}$/
    emailRegex: RegExp = /^\S+@\w+\.\w{2,6}(\.\w{2})?$/
    descRegex: RegExp = /^(?=.*[A-Za-z])([0-9])*.{3,100}$/
    cepRegex: RegExp = /^\d{5}-\d{3}$/

    protected abstract dadosPreenchidos(pessoa: Pessoa) : boolean
    protected abstract validacaoDadosFilhos(pessoa: Pessoa) : boolean

    protected dadosForamPreenchidos(pessoa: Pessoa) : boolean {
        return !(pessoa.nome && pessoa.email && pessoa.desc && pessoa.pais &&
            pessoa.estado && pessoa.cidade && pessoa.cep && pessoa.competencias.toString() && !this.dadosPreenchidos(pessoa))
    }

    validacao(pessoa: Pessoa) {
       if (this.dadosForamPreenchidos(pessoa)) {
           alert("Preencha todos os campos!")
           return false
       }

        if (!this.validacaoDadosPessoa(pessoa)) {
            return false
        }

        return this.validacaoDadosFilhos(pessoa);
    }

    protected validacaoDadosPessoa(pessoa: Pessoa) : boolean {
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

