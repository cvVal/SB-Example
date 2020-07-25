package com.cv.demo.kotapi.validator

import com.cv.demo.kotapi.domain.DemoGetResponse
import com.cv.demo.kotapi.domain.DemoGetResponseTransformer
import com.cv.demo.kotapi.model.Payment

object ValidatePayments {

    fun transformResponse(
            response: DemoGetResponse,
            paymentList: List<Payment>,
            patientId: String?
    ): List<DemoGetResponse.Payments>? {

        val paymentResponse: List<DemoGetResponse.Payments>?

        if (paymentList.isNotEmpty())
            paymentResponse = DemoGetResponseTransformer.transformPaymentsFromEntityToDomain(response, paymentList)
        else {
            paymentResponse = ArrayList()

            patientId?.let { response.patientId = it }
        }

        return paymentResponse
    }
}