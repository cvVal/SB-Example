package com.cv.demo.kotapi.repository

import com.cv.demo.kotapi.model.InvoicePayment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface InvoiceRepo : CrudRepository<InvoicePayment, Long> {

    fun findDistinctInvoicePaymentByInvoiceIdInAndPaymentDateGreaterThanEqualAndPaymentDateLessThan(
            invoiceId: List<String>,
            startDate: Date,
            endDate: Date
    ): List<InvoicePayment>

    fun findDistinctByPayorIdAndAndInvoiceIdInAndPorCAndPaymentDateGreaterThanEqualAndPaymentDateLessThan(
            patientId: String?,
            invoiceId: List<String>,
            porC:      String,
            startDate: Date,
            endDate: Date
    ): List<InvoicePayment>

}