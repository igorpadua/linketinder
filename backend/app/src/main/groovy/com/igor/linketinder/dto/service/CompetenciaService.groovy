package main.groovy.com.igor.linketinder.dto.service

import groovy.transform.TypeChecked
import main.groovy.com.igor.linketinder.entity.Competencia

@TypeChecked
class CompetenciaService {
    static ArrayList<Competencia> choiseCompetencia() {
        boolean end = true
        List<Competencia> aux = new ArrayList<>()
        Scanner scanner = new Scanner(System.in)

        while (end) {
            menu()
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
                    end = false
                    break
                default:
                    menu()
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
                return null
        }
    }

    private static void menu() {
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

    static List<Competencia> arrayCompetencia(String competencias) {
        // Remove os {} do array de competencias
        String remover = competencias.replace('{', '').replace('}', '')
        // Separa as competencias por virgula
        String[] competenciasArray = remover.split(',')
        // Transforma cada competencia
        List<Competencia> competenciasList = new ArrayList<>()
        for (String competencia in competenciasArray) {
            competenciasList.add(CompetenciaService.transformaString(competencia))
        }
        return competenciasList
    }
}
