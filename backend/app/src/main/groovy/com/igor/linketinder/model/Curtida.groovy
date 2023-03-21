package com.igor.linketinder.model

import groovy.transform.Canonical
import groovy.transform.TypeChecked

@TypeChecked
@Canonical
class Curtida {
    int idUsuario
    int idLink

    @Override
    String toString() {
        "Curtida(idUsuario: $idUsuario, idLink: $idLink)"
    }
}
