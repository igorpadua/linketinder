import Empresa from "../pessoa/entities/empresa.entity";
import ValidaPessoa from "./validarPessoa";

export default class ValidaEmpresa extends ValidaPessoa {
    static cnpjRegex: RegExp = /^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/

    private static dadosPreenchidos(empresa: Empresa) : boolean {
        return !(empresa.cnpj && !super.dadosForamPreenchidos(empresa))
    }

    static validacao(empresa: Empresa) {

        if (this.dadosPreenchidos(empresa)) {
            alert("Preencha todos os campos!")
            return false
        }

        if (!super.dadosPrincipais(empresa)) {
            return false
        }

        if (!this.cnpjRegex.test(empresa.cnpj)) {
            alert("CNPJ inválido!")
            return false
        }

        return true
    }
}