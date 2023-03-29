package com.igor.linketinder.util

import jakarta.servlet.http.HttpServletRequest
import spock.lang.Specification

class JsonTest extends Specification {

    def "Testa se está transformando uma requisição em um Map"() {
        given: "Uma requisição"
        HttpServletRequest request = Mock()
        request.getReader() >> new BufferedReader(new StringReader('{"nome": "João", "idade": 35}'))

        when: "Transforma a requisição em um Map"
        Map jsonMap = Json.formataJson(request)

        then: "O Map deve ser igual ao esperado"
        jsonMap.nome == "João"
        jsonMap.idade == 35
    }
}
