package com.igor.linketinder.util

import groovy.json.JsonSlurper
import groovy.transform.TypeChecked
import jakarta.servlet.http.HttpServletRequest
import org.apache.groovy.json.internal.LazyMap

@TypeChecked
class Json {
    static LazyMap formataJson(HttpServletRequest request) {
        StringBuilder json = new StringBuilder()
        BufferedReader reader = request.getReader()
        String line
        while ((line = reader.readLine()) != null) {
            json.append(line)
        }
        def jsonSlurper = new JsonSlurper()
        LazyMap jsonMap = jsonSlurper.parseText(json.toString()) as LazyMap
        return jsonMap
    }
}
