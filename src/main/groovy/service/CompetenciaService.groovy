package main.groovy.service

import main.groovy.entity.Competencia

class CompetenciaService {
    static ArrayList<Competencia> choiseCompetencia() {
        boolean end = true
        int opc = 0
        List<Competencia> aux = new ArrayList<>()
        Scanner scanner = new Scanner(System.in)

        menu()
        while (end) {
            opc = scanner.nextInt()
            switch (opc) {
                case 1:
                    aux.add(Competencia.Python)
                    print("Escolha outra opção: ")
                    break
                case 2:
                    aux.add(Competencia.Java)
                    print("Escolha outra opção: ")
                    break
                case 3:
                    aux.add(Competencia.SpringFramework)
                    print("Escolha outra opção: ")
                    break
                case 4:
                    aux.add(Competencia.Angular)
                    print("Escolha outra opção: ")
                    break
                case 5:
                    aux.add(Competencia.cplusplus)
                    print("Escolha outra opção: ")
                    break
                case 6:
                    aux.add(Competencia.c)
                    print("Escolha outra opção: ")
                    break
                case 7:
                    aux.add(Competencia.JavaScript)
                    print("Escolha outra opção: ")
                    break
                case 8:
                    aux.add(Competencia.Html)
                    print("Escolha outra opção: ")
                    break
                case 9:
                    aux.add(Competencia.Node)
                    print("Escolha outra opção: ")
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
}
