package com.cv.demo.kotapi.service

import com.cv.demo.kotapi.constants.Constants.DATE_FORMAT
import com.cv.demo.kotapi.constants.Constants.PORC
import com.cv.demo.kotapi.domain.DemoGetResponse
import com.cv.demo.kotapi.exceptions.BusinessException
import com.cv.demo.kotapi.model.Payment
import com.cv.demo.kotapi.repository.InvoiceRepo
import com.cv.demo.kotapi.repository.PaymentRepo
import com.cv.demo.kotapi.validator.ValidateDates.addDaysToDate
import com.cv.demo.kotapi.validator.ValidateDates.validateDates
import com.cv.demo.kotapi.validator.ValidateInvoices.validateInvoice
import com.cv.demo.kotapi.validator.ValidatePayments.transformResponse
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DemoServiceImpl(
        val patientService: PatientService?,
        val invoiceRepo: InvoiceRepo?,
        val paymentRepo: PaymentRepo?
) : DemoService {
    val maxDaysRange = "720"
    val defaultDaysRange = "540"

    @Throws(Exception::class)
    override fun getPayments(patientId: String?, invoiceId: List<String>?,
                             startDate: String?, endDate: String?
    ): DemoGetResponse {

        LOGGER.info("Start ServiceImpl.getPayments")

        var startDateValue = startDate
        var endDateValue = endDate

        patientId?.let { patientService!!.getPatientById(it) }

        validateDates(startDateValue, endDateValue, maxDaysRange)

        val dateTimeFormatter = DateTimeFormat.forPattern(DATE_FORMAT)

        if (startDateValue == null && endDateValue == null) {

            val defaultStartDate = LocalDate.now().minusDays(defaultDaysRange.toInt())

            startDateValue = defaultStartDate.toString(dateTimeFormatter)

            val todayDay = LocalDate.now()
            endDateValue = todayDay.toString(dateTimeFormatter)
        }

        val startDay = LocalDate.parse(startDateValue, dateTimeFormatter)
        val endDay = LocalDate.parse(endDateValue, dateTimeFormatter)

        val demoResponse: List<DemoGetResponse.Payments>?

        val response = DemoGetResponse()

        LOGGER.debug("getPayments is called with data: Start date - $startDay End date - $endDay Patient - $patientId & Invoice(s) - $invoiceId")

        try {
            when {

                patientId != null && invoiceId.isNullOrEmpty() -> {

                    LOGGER.info("Start search by patientId")

                    val paymentList = paymentRepo!!
                            .findByPaymentPrimaryKeyPatientIdAndPaymentPrimaryKeyPaymentDateBetweenAndPaymentPrimaryKeyPorCOrderByPaymentPrimaryKeyPaymentDateDesc(
                                    patientId, startDay.toDate(), endDay.toDate(), PORC
                            )

                    demoResponse = transformResponse(response, paymentList, patientId)

                    response.payments = demoResponse

                    LOGGER.info("End search by patientId")
                }

                invoiceId!!.isNotEmpty() && patientId == null -> {

                    LOGGER.info("Start search by invoiceId")

                    val invoiceList = ArrayList<String>()

                    invoiceId.forEach { invoice -> validateInvoice(invoice, invoiceList) }

                    val invoicesPayments = invoiceRepo!!
                            .findDistinctInvoicePaymentByInvoiceIdInAndPaymentDateGreaterThanEqualAndPaymentDateLessThan(
                                    invoiceList, startDay.toDate(), addDaysToDate(endDay, 1).toDate()
                            )

                    val paymentList = ArrayList<Payment>()

                    invoicesPayments.forEach { payments -> paymentList.add(payments.payment!!) }

                    demoResponse = transformResponse(response, paymentList, patientId)

                    response.payments = demoResponse

                    LOGGER.info("End search by invoiceId")
                }

                patientId != null && invoiceId.isNotEmpty() -> {

                    LOGGER.info("Start search by patientId & invoiceId")

                    val invoiceList = ArrayList<String>()

                    invoiceId.forEach { invoice -> validateInvoice(invoice, invoiceList) }

                    val invoicesPayments = invoiceRepo!!
                            .findDistinctByPayorIdAndAndInvoiceIdInAndPorCAndPaymentDateGreaterThanEqualAndPaymentDateLessThan(
                                    patientId, invoiceList, PORC, startDay.toDate(), addDaysToDate(endDay, 1).toDate()
                            )

                    val paymentList = ArrayList<Payment>()

                    invoicesPayments.forEach { payments -> paymentList.add(payments.payment!!) }

                    demoResponse = transformResponse(response, paymentList, patientId)

                    response.payments = demoResponse

                    LOGGER.info("End search by patientId & invoiceId")
                }

                else -> throw BusinessException("You must fill either patientId or invoiceId")
            }
        } catch (e: Exception) {
            throw BusinessException(e.message!!)
        }

        return response
    }

    companion object { private val LOGGER = LoggerFactory.getLogger(DemoServiceImpl::class.java) }
}