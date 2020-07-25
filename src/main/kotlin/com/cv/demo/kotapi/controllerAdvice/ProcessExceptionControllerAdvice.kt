package com.cv.demo.kotapi.controllerAdvice

import com.cv.demo.kotapi.constants.Constants.DATE_TIME_FORMAT
import com.cv.demo.kotapi.exceptions.ErrorResponse
import com.cv.demo.kotapi.exceptions.ProcessException
import org.joda.time.LocalDateTime
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ProcessExceptionControllerAdvice {

    @ExceptionHandler(ProcessException::class)
    fun processException(processException: ProcessException): ResponseEntity<ErrorResponse> {

        val timeStamp = LocalDateTime.now()
        val response = ErrorResponse(
                timeStamp.toString(DATE_TIME_FORMAT), "500", "Process Exception", processException.message)

        return ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}