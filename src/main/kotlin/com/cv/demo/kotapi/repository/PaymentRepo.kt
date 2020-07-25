package com.cv.demo.kotapi.repository

import com.cv.demo.kotapi.model.Payment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PaymentRepo : CrudRepository<Payment, String> {

    fun findByPaymentPrimaryKeyPatientIdAndPaymentPrimaryKeyPaymentDateBetweenAndPaymentPrimaryKeyPorCOrderByPaymentPrimaryKeyPaymentDateDesc(
            patientId: String,
            startDate: Date,
            endDate: Date,
            pOrC: String
    ): List<Payment>
}
