package org.kotlin.springboot.rest.handler

import org.kotlin.springboot.domain.entity.CompanyNotFoundException
import org.kotlin.springboot.domain.entity.ProductNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(CompanyNotFoundException::class)
    fun handleCompanyNotFoundException(): ResponseEntity<Unit> = ResponseEntity.notFound().build()

    @ExceptionHandler(ProductNotFoundException::class)
    fun handleProductNotFoundException(): ResponseEntity<Unit> = ResponseEntity.notFound().build()

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNoHandlerFoundException(): ResponseEntity<Unit> = ResponseEntity.notFound().build()

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(): ResponseEntity<Unit> = ResponseEntity.notFound().build()

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(): ResponseEntity<Unit> = ResponseEntity.status(METHOD_NOT_ALLOWED).build()

    @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
    fun handleHttpMediaTypeNotSupportedException(): ResponseEntity<Unit> = ResponseEntity.status(UNSUPPORTED_MEDIA_TYPE).build()

    @ExceptionHandler(Throwable::class)
    fun handleThrowable(ex: Throwable): ResponseEntity<Unit> {
        LoggerFactory.getLogger(this::class.java).error("Internal server error", ex)
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).build()
    }
}