package com.cv.demo.kotapi.exceptions

class NotFoundException(override val message: String) : RuntimeException()

class BusinessException(override val message: String = "Conflict") : RuntimeException()

class BadRequestException(override val message: String = "Invalid request") : RuntimeException()

class ProcessException(override val message: String) : RuntimeException()