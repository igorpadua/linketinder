import Candidato from "../pessoa/entities/candidato.entity";
import ValidaPessoa from "./validarPessoa";

export class ValidaCandidato extends ValidaPessoa {
    static cpfRegex: RegExp = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/
    static idadeRegex: RegExp = /^\d{2}$/

    private static dadosPreenchidos(candidato: Candidato) : boolean {
        return !(candidato.cpf && candidato.idade && !super.dadosForamPreenchidos(candidato))
    }

    static validacao(candidato: Candidato) {

        if (this.dadosPreenchidos(candidato)) {
            alert("Preencha todos os campos!")
            return false
        }

        if (!super.dadosPrincipais(candidato)) {
            return false
        }

        if (!this.cpfRegex.test(candidato.cpf)) {
            alert("CPF inválido!")
            return false
        }
        if (!this.idadeRegex.test(candidato.idade.toString())) {
            alert("Idade inválida!")
            return false
        }
        return true
    }

}
