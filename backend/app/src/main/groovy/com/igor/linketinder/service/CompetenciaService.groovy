package com.igor.linketinder.service

import groovy.transform.TypeChecked
import com.igor.linketinder.entity.Competencia

@TypeChecked
class CompetenciaService {

    static ArrayList<Competencia> escolherCompetencias() {
        boolean end = true
        List<Competencia> aux = new ArrayList<>()
        Scanner scanner = new Scanner(System.in)

        while (end) {
            menuEscolhaCompetencias()
            int opc = scanner.nextInt()
            switch (opc) {
                case 1:
                    aux.add(Competencia.Python)
                    break
                case 2:
                    aux.add(Competencia.Java)
                    break
                case 3:
                    aux.add(Competencia.SpringFramework)
                    break
                case 4:
                    aux.add(Competencia.Angular)
                    break
                case 5:
                    aux.add(Competencia.cplusplus)
                    break
                case 6:
                    aux.add(Competencia.c)
                    break
                case 7:
                    aux.add(Competencia.JavaScript)
                    break
                case 8:
                    aux.add(Competencia.Html)
                    break
                case 9:
                    aux.add(Competencia.Node)
                    break
                case 10:
                    if (aux.isEmpty()) {
                        println("Nenhuma competencia adicionada")
                        break
                    }
                    end = false
                    break
                default:
                    println("Opção incorreta")
            }

        }
        return aux
    }

    static Competencia transformaString(String competencia) {
        switch (competencia) {
            case "Python":
                return Competencia.Python
            case "Java":
                return Competencia.Java
            case "SpringFramework":
                return Competencia.SpringFramework
            case "Angular":
                return Competencia.Angular
            case "cplusplus":
                return Competencia.cplusplus
            case "c":
                return Competencia.c
            case "JavaScript":
                return Competencia.JavaScript
            case "Html":
                return Competencia.Html
            case "Node":
                return Competencia.Node
            default:
                throw new IllegalArgumentException("Competencia não encontrada")
        }
    }

    private static void menuEscolhaCompetencias() {
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

    static List<Competencia> transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(String competencias) {
        String removeChaves = competencias.replace('{', '').replace('}', '')
        String[] separaPorVirgula = removeChaves.split(',')
        List<Competencia> competenciasList = new ArrayList<>()
        for (String competencia in separaPorVirgula) {
            competenciasList.add(transformaString(competencia))
        }
        return competenciasList
    }
}
