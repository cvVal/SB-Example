package com.cv.demo.kotapi.controller

import com.cv.demo.kotapi.domain.DemoGetResponse
import com.cv.demo.kotapi.exceptions.ErrorResponse
import com.cv.demo.kotapi.exceptions.NotFoundException
import com.cv.demo.kotapi.service.DemoService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.format.annotation.NumberFormat
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Digits
import javax.validation.constraints.Size

@RestController
@RequestMapping(value = ["/v1/myrequest"], produces = [MediaType.APPLICATION_JSON_VALUE])
@Validated
class MyDemoController(
        val demoService: DemoService?
) {

    @GetMapping(value = ["/"])
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
        value = "Get Demo",
        notes = "Some notes here",
        response = DemoGetResponse::class
    )
    @ApiResponses(
        value = [
            (ApiResponse(code = 200, message = "Successful operation", response = DemoGetResponse::class)),
            (ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse::class)),
            (ApiResponse(code = 409, message = "Business Exception", response = ErrorResponse::class)),
            (ApiResponse(code = 500, message = "Internal server error", response = ErrorResponse::class))
        ]
    )
    fun getPayments(

        @Valid
        @NumberFormat(style = NumberFormat.Style.NUMBER)
        @Digits(integer = 10, fraction = 0, message = "Must be number between 1 and 10")
        @RequestParam(value = "patientId", required = false)
        patientId: String?,

        @RequestParam(value = "invoiceId", required = false)
        invoiceId: List<String>?,

        @Valid
        @Size(max = 10, message = "Date must match with yyyy-MM-dd format")
        @RequestParam(value = "startDate", required = false)
        startDate: String?,

        @Valid
        @Size(max = 10, message = "Date must match with yyyy-MM-dd format")
        @RequestParam(value = "endDate", required = false)
        endDate: String?
    ): DemoGetResponse = demoService!!.getPayments(patientId, invoiceId, startDate, endDate)

    @GetMapping(value = ["**"])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun pathNotSupported(): String = throw NotFoundException("Requested path not supported")
}