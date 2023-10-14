package com.softwalter.springwebfluxessentials.exception

import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.server.ResponseStatusException

class CustomAttributes : DefaultErrorAttributes() {

    override fun getErrorAttributes(request: ServerRequest, options: ErrorAttributeOptions): Map<String, Any> {
        val errorAttributesMap = super.getErrorAttributes(request, options)
        val throwable = getError(request)
        if (throwable is ResponseStatusException) {
            val ex = throwable as ResponseStatusException
            errorAttributesMap["message"] = ex.reason
            errorAttributesMap["developerMessage"] = "A ResponseStatusException Happened"
        }
        return errorAttributesMap
    }
}