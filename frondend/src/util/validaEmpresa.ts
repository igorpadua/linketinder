import Empresa from "../model/empresa.entity";
import ValidaPessoa from "./validarPessoa";

export default class ValidaEmpresa extends ValidaPessoa {
    cnpjRegex: RegExp = /^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/

    protected dadosPreenchidos(empresa: Empresa) : boolean {
        return !(empresa.cnpj)
    }

    protected validacaoDadosFilhos(empresa: Empresa): boolean {
        if (!this.cnpjRegex.test(empresa.cnpj)) {
            alert("CNPJ inválido!")
            return false
        }
        return true
    }
}