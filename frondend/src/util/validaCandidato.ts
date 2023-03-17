import Candidato from "../model/candidato.entity";
import ValidaPessoa from "./validarPessoa";

export class ValidaCandidato extends ValidaPessoa {
    cpfRegex: RegExp = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/

    protected dadosPreenchidos(candidato: Candidato) : boolean {
        return !(candidato.cpf)
    }

    protected validacaoDadosFilhos(candidato: Candidato): boolean {
        if (!this.cpfRegex.test(candidato.cpf)) {
            alert("CPF inválido!")
            return false
        }
        return true;
    }
}
