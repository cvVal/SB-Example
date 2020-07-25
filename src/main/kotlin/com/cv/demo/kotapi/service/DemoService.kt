package com.cv.demo.kotapi.service

import com.cv.demo.kotapi.domain.DemoGetResponse

interface DemoService {

    @Throws(Exception::class)
    fun getPayments(
            patientId: String?,
            invoiceId: List<String>?,
            startDate: String?,
            endDate: String?
    ): DemoGetResponse
}