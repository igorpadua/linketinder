package com.igor.linketinder.view

import groovy.transform.TypeChecked
import com.igor.linketinder.model.Competencia

@TypeChecked
class CompetenciaView {

    static ArrayList<Competencia> escolherCompetencias() {
        boolean finalizarEscolhas = true
        List<Competencia> listasCompetencias = new ArrayList<>()
        Scanner scanner = new Scanner(System.in)

        while (finalizarEscolhas) {
            menuEscolhaCompetencias()
            int opc = scanner.nextInt()
            switch (opc) {
                case 1:
                    listasCompetencias.add(Competencia.python)
                    break
                case 2:
                    listasCompetencias.add(Competencia.java)
                    break
                case 3:
                    listasCompetencias.add(Competencia.springframework)
                    break
                case 4:
                    listasCompetencias.add(Competencia.angular)
                    break
                case 5:
                    listasCompetencias.add(Competencia.cplusplus)
                    break
                case 6:
                    listasCompetencias.add(Competencia.c)
                    break
                case 7:
                    listasCompetencias.add(Competencia.javascript)
                    break
                case 8:
                    listasCompetencias.add(Competencia.html)
                    break
                case 9:
                    listasCompetencias.add(Competencia.node)
                    break
                case 10:
                    if (listasCompetencias.isEmpty()) {
                        println("Nenhuma competencia adicionada")
                        break
                    }
                    finalizarEscolhas = false
                    break
                default:
                    println("Opção incorreta")
            }

        }
        return listasCompetencias
    }

    static void menuEscolhaCompetencias() {
        println("Escolhas as competencias: ")
        println("1 - Python")
        println("2 - Java")
        println("3 - SpringFramework")
        println("4 - Angular")
        println("5 - C++")
        println("6 - C")
        println("7 - JavaScript")
        println("8 - HTML")
        println("9 - Node")
        println("10 - Sair")
    }

}
