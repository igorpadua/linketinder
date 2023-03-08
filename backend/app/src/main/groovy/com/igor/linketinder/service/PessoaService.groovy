package com.igor.linketinder.service

import java.util.regex.Pattern

class PessoaService {
    static boolean validaCEP(String cep) {
        if (cep == null || cep.isEmpty()) return false
        if (cep.length() != 9) return false
        Pattern cepRegex = ~/^\d{5}-\d{3}$/
        if (!cepRegex.matcher(cep).matches()) return false
        return true
    }

    static String pegaCep() {
        Scanner scanner = new Scanner(System.in)
        print("Digite o CEP da empresa: ")
        String cep = scanner.nextLine()
        while (!validaCEP(cep)) {
            println("CEP inválido")
            print("Digite o CEP da empresa: ")
            cep = scanner.nextLine()
        }
        return cep
    }
}
