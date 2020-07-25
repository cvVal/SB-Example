package com.cv.demo.kotapi.controllerAdvice

import com.cv.demo.kotapi.constants.Constants.DATE_TIME_FORMAT
import com.cv.demo.kotapi.exceptions.ErrorResponse
import com.cv.demo.kotapi.exceptions.BadRequestException
import org.joda.time.LocalDateTime
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class BadRequestControllerAdvice {

    @ExceptionHandler(BadRequestException::class)
    fun badRequestException(badRequest: BadRequestException): ResponseEntity<ErrorResponse> {

        val timeStamp = LocalDateTime.now()
        val response = ErrorResponse(
                timeStamp.toString(DATE_TIME_FORMAT), "400", HttpStatus.BAD_REQUEST.name, badRequest.message)

        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }
}