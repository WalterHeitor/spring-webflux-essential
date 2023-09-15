package com.softwalter.springwebfluxessentials.exception

//import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
//import org.springframework.boot.web.error.ErrorAttributeOptions
//import org.springframework.boot.web.reactive.error.ErrorAttributes
//import org.springframework.context.ApplicationContext
//import org.springframework.core.annotation.Order
//import org.springframework.http.MediaType
//import org.springframework.http.codec.ServerCodecConfigurer
//import org.springframework.stereotype.Component
//import org.springframework.web.reactive.function.BodyInserters
//import org.springframework.web.reactive.function.server.*
//import reactor.core.publisher.Mono

import org.springframework.boot.autoconfigure.web.ResourceProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.error.ErrorAttributeOptions.Include
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono

@Component
@Order(-2)
class GlobalExceptionHandler(
        errorAttributes: ErrorAttributes,
        resourceProperties: ResourceProperties,
        applicationContext: ApplicationContext,
        codecConfigurer: ServerCodecConfigurer
) : AbstractErrorWebExceptionHandler(errorAttributes, resourceProperties, applicationContext) {

    init {
        this.setMessageWriters(codecConfigurer.writers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes): RouterFunction<ServerResponse> {
        return RouterFunctions.route(RequestPredicates.all(), this::formatErrorResponse)
    }

    private fun formatErrorResponse(request: ServerRequest): Mono<ServerResponse> {
        val errorAttributesMap = getErrorAttributes(request, ErrorAttributeOptions.of(Include.STACK_TRACE))
        val status = errorAttributesMap["status"] as? Int ?: 500
        return ServerResponse
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorAttributesMap))
    }
}
//@Component
//@Order(-2)
//class GlobalExceptionHandler(
//        errorAttributes: ErrorAttributes,
//        resourceProperties: ResourceProperties,
//        applicationContext: ApplicationContext,
//        codecConfigurer: ServerCodecConfigurer
//) : AbstractErrorWebExceptionHandler(errorAttributes, resourceProperties, applicationContext) {
//    init {
//        this.setMessageWriters(codecConfigurer.writers)
//    }
//
//    override fun getRoutingFunction(errorAttributes: ErrorAttributes): RouterFunction<ServerResponse> {
//        return RouterFunctions.route(RequestPredicates.all(), this::formatErrorResponse)
//    }
//
//    private fun formatErrorResponse(request: ServerRequest): Mono<ServerResponse> {
//        val errorAttributesMap = getErrorAttributes(request, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE))
//        val status = errorAttributesMap["status"] as? Int ?: 500
//        return ServerResponse
//                .status(status)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromValue(errorAttributesMap))
//    }
//}

