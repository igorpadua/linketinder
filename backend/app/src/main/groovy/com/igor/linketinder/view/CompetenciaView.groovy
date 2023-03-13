package com.igor.linketinder.view

import groovy.transform.TypeChecked
import com.igor.linketinder.model.TipoCompetencia

@TypeChecked
class CompetenciaView {

    static ArrayList<TipoCompetencia> escolherCompetencias() {
        boolean finalizarEscolhas = true
        List<TipoCompetencia> listasCompetencias = new ArrayList<>()
        Scanner scanner = new Scanner(System.in)

        while (finalizarEscolhas) {
            menuEscolhaCompetencias()
            int opc = scanner.nextInt()
            switch (opc) {
                case 1:
                    listasCompetencias.add(TipoCompetencia.python)
                    break
                case 2:
                    listasCompetencias.add(TipoCompetencia.java)
                    break
                case 3:
                    listasCompetencias.add(TipoCompetencia.springframework)
                    break
                case 4:
                    listasCompetencias.add(TipoCompetencia.angular)
                    break
                case 5:
                    listasCompetencias.add(TipoCompetencia.cplusplus)
                    break
                case 6:
                    listasCompetencias.add(TipoCompetencia.c)
                    break
                case 7:
                    listasCompetencias.add(TipoCompetencia.javascript)
                    break
                case 8:
                    listasCompetencias.add(TipoCompetencia.html)
                    break
                case 9:
                    listasCompetencias.add(TipoCompetencia.node)
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
