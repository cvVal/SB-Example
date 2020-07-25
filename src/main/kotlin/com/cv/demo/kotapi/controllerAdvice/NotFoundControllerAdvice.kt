package com.cv.demo.kotapi.controllerAdvice

import com.cv.demo.kotapi.constants.Constants.DATE_TIME_FORMAT
import com.cv.demo.kotapi.exceptions.ErrorResponse
import com.cv.demo.kotapi.exceptions.NotFoundException
import org.joda.time.LocalDateTime
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class NotFoundControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(notFound: NotFoundException): ResponseEntity<ErrorResponse> {

        val timeStamp = LocalDateTime.now()
        val response = ErrorResponse(
                timeStamp.toString(DATE_TIME_FORMAT), "404", HttpStatus.NOT_FOUND.name, notFound.message)

        return ResponseEntity(response, HttpStatus.NOT_FOUND)
    }
}