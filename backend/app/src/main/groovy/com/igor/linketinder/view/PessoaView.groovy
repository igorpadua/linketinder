package com.igor.linketinder.view

import com.igor.linketinder.entity.Pessoa

import java.util.regex.Pattern

class PessoaView {
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

    private static Boolean listaEstaValida(List<Pessoa> pessoas) {
        return pessoas != null && !pessoas.isEmpty()
    }

    static void imprimir(List<Pessoa> pessoas) {
        if (!listaEstaValida(pessoas)) return

        for (pessoa in pessoas) {
            println(pessoa)
        }
    }
}
