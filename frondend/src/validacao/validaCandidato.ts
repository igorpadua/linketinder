import Candidato from "../pessoa/entities/candidato.entity";
import ValidaPessoa from "./validarPessoa";

export class ValidaCandidato extends ValidaPessoa {
    cpfRegex: RegExp = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/
    idadeRegex: RegExp = /^\d{2}$/

    protected dadosPreenchidos(candidato: Candidato) : boolean {
        return !(candidato.cpf && candidato.idade)
    }

    protected validacaoDadosFilhos(candidato: Candidato): boolean {
        if (!this.idadeRegex.test(candidato.idade.toString())) {
            alert("Idade inválida!")
            return false
        }
        if (!this.cpfRegex.test(candidato.cpf)) {
            alert("CPF inválido!")
            return false
        }
        return true;
    }
}
