package com.igor.linketinder.util

import java.util.regex.Pattern

class Validacoes {
    static boolean validaCEP(String cep) {
        if (cep == null || cep.isEmpty()) return false
        final Pattern cepRegex = ~/^\d{5}-\d{3}$/
        if (!cepRegex.matcher(cep).matches()) return false
        return true
    }

    static Boolean validaCNPJ(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) return false
        final Pattern cnpjRegex = ~/^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/
        if (!cnpjRegex.matcher(cnpj).matches()) return false
        return true
    }

    static Boolean validaCPF(String cpf) {
        if (cpf == null || cpf.isEmpty()) return false
        final Pattern cpfRegex = ~/^\d{3}\.\d{3}\.\d{3}-\d{2}$/
        if (!cpfRegex.matcher(cpf).matches()) return false
        return true
    }

}
