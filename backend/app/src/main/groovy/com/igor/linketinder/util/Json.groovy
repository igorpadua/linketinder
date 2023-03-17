package com.igor.linketinder.util

import groovy.json.JsonSlurper
import jakarta.servlet.http.HttpServletRequest

class Json {
    def formataJson(HttpServletRequest request) {
        StringBuilder json = new StringBuilder()
        BufferedReader reader = request.getReader()
        String line
        while ((line = reader.readLine()) != null) {
            json.append(line)
        }
        def jsonSlurper = new JsonSlurper()
        def jsonMap = jsonSlurper.parseText(json.toString())
        return jsonMap
    }
}
