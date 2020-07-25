package com.cv.demo.kotapi.exceptions

data class ErrorResponse(
        val timeStamp: String,
        val status: String,
        val error: String,
        val message: String
)