package com.igor.linketinder.model

import groovy.transform.Canonical
import groovy.transform.TypeChecked

@TypeChecked
@Canonical
class Competencia {
    List<TipoCompetencia> competencias

    static List<TipoCompetencia> transformaUmArryDeStringDeCompetenciaEmUmaListaDeCompetencia(String competencias) {
        String removeChaves = competencias.replace('{', '').replace('}', '')
        String removeEspacos = removeChaves.replace(' ', '')
        String[] separaPorVirgula = removeEspacos.split(',')
        List<TipoCompetencia> competenciasList = new ArrayList<>()
        for (String competencia in separaPorVirgula) {
            try {
                competenciasList.add(TipoCompetencia.valueOf(competencia.toLowerCase()))
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
