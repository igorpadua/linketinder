package com.igor.linketinder.service

import com.igor.linketinder.model.Competencia
import com.igor.linketinder.view.CompetenciaView

class CompetenciaService {

    static List<Competencia> transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(String competencias) {
        String removeChaves = competencias.replace('{', '').replace('}', '')
        String removeEspacos = removeChaves.replace(' ', '')
        String[] separaPorVirgula = removeEspacos.split(',')
        List<Competencia> competenciasList = new ArrayList<>()
        for (String competencia in separaPorVirgula) {
            try {
                competenciasList.add(Competencia.valueOf(competencia.toLowerCase()))
            } catch (IllegalArgumentException e) {
                if (competencia == "") {
                    continue
                }
                throw new RuntimeException("Não foi possível encontrar uma competência com o nome fornecido.", e)
            }
        }
        return competenciasList
    }

    static ArrayList<Competencia> escolherCompetencias() {
        boolean finalizarEscolhas = true
        List<Competencia> listasCompetencias = new ArrayList<>()
        Scanner scanner = new Scanner(System.in)

        while (finalizarEscolhas) {
            CompetenciaView.menuEscolhaCompetencias()
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
}
