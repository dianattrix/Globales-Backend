package com.globales.farmastock

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

// Base de todos los errores.
data class ApiSubError(
    val code: String? = "NO-CODE",
    val message: String? = "NO MESSAGE",
)


// Detalle del error, encapsula el objeto
data class ApiError(
    val localDateTime: String = LocalDateTime.now().toString(),
    val status: HttpStatus,
    val message: String? = null,
    val debugMessage: String? = null,
    var apiSubErrors: MutableList<ApiSubError> = mutableListOf(),
) {
    fun addSubError(apiError: ApiSubError) {
        apiSubErrors.add(apiError)
    }
}

// ControllerAdvice: cuando ocurre un error, spring se mete.
@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    val logger: Logger = LoggerFactory.getLogger(RestExceptionHandler::class.java)

    fun buildResponseEntity(apiError: ApiError): ResponseEntity<Any>? {
        return ResponseEntity(apiError, apiError.status)
    }

    // Exepcion para dato no enontrado.
    @ExceptionHandler(NoSuchElementException::class)
    fun elementNotFound(
        ex: java.lang.Exception,
        request: WebRequest,
    ): ResponseEntity<Any>? {
        val apiError = ApiError(
            message = "error occurred",
            debugMessage = ex.message,
            status = HttpStatus.NOT_FOUND,
        )

        apiError.addSubError(ApiSubError("ELEMENT_NOT_FOUND", "Element not found"))
        logger.debug("BackEnd - FARMASTOCK {}", ex)

        return buildResponseEntity(apiError)
    }

    // Exception generica, agarra lo que quiera.
    @ExceptionHandler(Exception::class)
    fun handleAll(
        ex: java.lang.Exception,
        request: WebRequest,
    ): ResponseEntity<Any>? {

        val apiError = ApiError(
            message = "error occurred",
            debugMessage = ex.message,
            status = HttpStatus.INTERNAL_SERVER_ERROR,
        )

        apiError.addSubError(ApiSubError("INTERNAL_ERROR", "There is a serious error in the system"))
        logger.debug("BackEnd - FARMASTOCK {}", ex)

        return buildResponseEntity(apiError)
    }
}