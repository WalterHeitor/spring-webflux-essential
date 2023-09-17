package com.softwalter.springwebfluxessentials.exception

import org.springframework.boot.autoconfigure.web.ResourceProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.error.ErrorAttributeOptions.Include
import org.springframework.boot.web.error.ErrorAttributeOptions.of
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono

@Component
@Order(-2)
abstract class GlobalExceptionHandler(
        private val errorAttributes: ErrorAttributes,
        private val resourceProperties: ResourceProperties,
        private val applicationContext: ApplicationContext,
        private val codecConfigurer: ServerCodecConfigurer
) : AbstractErrorWebExceptionHandler(errorAttributes, resourceProperties, applicationContext) {

    init {
        setMessageWriters(codecConfigurer.writers)
    }

    @Bean
    fun globalErrorRouterFunction(): RouterFunction<ServerResponse> {
        return RouterFunctions.route(RequestPredicates.all(), this::formatErrorResponse)
    }

    private fun formatErrorResponse(request: ServerRequest): Mono<ServerResponse> {
        val query = request.uri().query
        val errorAttributeOptions = if (isTraceEnabled(query)) of(Include.STACK_TRACE) else ErrorAttributeOptions.defaults()

        val errorAttributesMap = getErrorAttributes(request, errorAttributeOptions)
        val status = errorAttributesMap["status"] as? Int ?: 500

        return ServerResponse
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorAttributesMap))
    }

    private fun isTraceEnabled(query: String?): Boolean {
        return !StringUtils.isEmpty(query) && query!!.contains("trace=true")
    }
}