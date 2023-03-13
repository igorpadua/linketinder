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

}
