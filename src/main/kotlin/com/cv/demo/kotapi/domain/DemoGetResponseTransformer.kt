package com.cv.demo.kotapi.domain

import com.cv.demo.kotapi.constants.Constants.CHECK_PAYMENT
import com.cv.demo.kotapi.constants.Constants.C_CARD_PAYMENT
import com.cv.demo.kotapi.constants.Constants.DATE_FORMAT
import com.cv.demo.kotapi.constants.Constants.PROGRESS_STATUS
import com.cv.demo.kotapi.constants.Constants.SUCCESS_STATUS
import com.cv.demo.kotapi.constants.Constants.TIME_FORMAT
import com.cv.demo.kotapi.model.Deposit
import com.cv.demo.kotapi.model.InvoicePayment
import com.cv.demo.kotapi.model.Payment
import org.joda.time.DateTime
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat

object DemoGetResponseTransformer {

    fun transformPaymentsFromEntityToDomain(response: DemoGetResponse, paymentsEntity: List<Payment>): List<DemoGetResponse.Payments> {

        val listOfPayments = ArrayList<DemoGetResponse.Payments>()

        val dateTimeFormatter = DateTimeFormat.forPattern(DATE_FORMAT)
        val timeFormatter = DateTimeFormat.forPattern(TIME_FORMAT)

        paymentsEntity.forEach { payment ->

            response.patientId = payment.paymentPrimaryKey!!.patientId

            val paymentsResponse = DemoGetResponse.Payments()

            var paymentTime: LocalDateTime? = null

            payment.creditCard!!.forEach { timeCardHistory ->

                paymentTime = DateTime(timeCardHistory.paymentDate).toLocalDateTime()
            }

            val paymentDate = payment.paymentPrimaryKey!!.paymentDate
            val jodaDate = DateTime(paymentDate)
            paymentsResponse.paymentDate = jodaDate.toString(dateTimeFormatter)

            paymentsResponse.amount = payment.amount

            when {
                payment.creditCard!!.isEmpty() -> {

                    val paymentTimeOnly = payment.paymentPrimaryKey!!.paymentDate
                    val jodaTime = DateTime(paymentTimeOnly)
                    paymentsResponse.paymentTime = jodaTime.toString(timeFormatter)

                    paymentsResponse.paymentType = CHECK_PAYMENT
                    paymentsResponse.checkNo = payment.paymentPrimaryKey!!.checkNo
                    paymentsResponse.transactionId = null
                    paymentsResponse.confirmationNo = null
                    paymentsResponse.cardId = null

                    if (payment.deposit!!.completedFlag.equals("Y"))
                        paymentsResponse.paymentStatus = SUCCESS_STATUS
                    else
                        paymentsResponse.paymentStatus = PROGRESS_STATUS

                    paymentsResponse.rejectReason = null
                    paymentsResponse.reference = null
                    paymentsResponse.type = null
                }
                else -> {
                    paymentsResponse.paymentTime = paymentTime!!.toString(timeFormatter)
                    
                    paymentsResponse.paymentType = C_CARD_PAYMENT
                    paymentsResponse.checkNo = payment.paymentPrimaryKey!!.checkNo
                    paymentsResponse.transactionId = payment.creditCard!![0].transactionId
                    paymentsResponse.confirmationNo = payment.creditCard!![0].confirmationNo
                    paymentsResponse.cardId = payment.creditCard!![0].cardId
                    paymentsResponse.paymentStatus = payment.creditCard!![0].paymentStatus
                    
                    if (payment.creditCard!![0].paymentStatus.equals(SUCCESS_STATUS))
                        paymentsResponse.rejectReason = null
                    else
                        paymentsResponse.rejectReason = payment.creditCard!![0].rejectReason
                    
                    paymentsResponse.reference = payment.creditCard!![0].reference
                    paymentsResponse.type = payment.creditCard!![0].type
                }
            }
            
            when {
                payment.invoicePayment == null -> paymentsResponse.invoices = null
                else -> paymentsResponse.invoices = transformInvoicesToDomain(payment.invoicePayment)
            }

            when {
                payment.deposit == null -> paymentsResponse.deposit = null
                else -> paymentsResponse.deposit = transformDepositToDomain(payment.deposit)
            }

            listOfPayments.add(paymentsResponse)
        }

        return listOfPayments
    }

    private fun transformInvoicesToDomain(invoiceEntity: List<InvoicePayment>?): List<DemoGetResponse.Invoice> {

        val invoiceDomainList = ArrayList<DemoGetResponse.Invoice>()

        invoiceEntity!!.indices.forEach { i ->

            val invoice = DemoGetResponse.Invoice()

            invoice.invoiceId = invoiceEntity[i].invoiceId!!.toString() + '-'.toString() + invoiceEntity[i].invoiceSeq!!.toString()
            invoiceDomainList.add(invoice)
        }

        return invoiceDomainList
    }

    private fun transformDepositToDomain(depositEntity: Deposit?): DemoGetResponse.Deposit {

        val depositDomain = DemoGetResponse.Deposit()

        when {
            depositEntity!!.bank == null -> {

                depositDomain.depositId = depositEntity.depositId!!.toString()
                depositDomain.bank = null
            }
            else -> {

                depositDomain.depositId = depositEntity.depositId!!.toString()
                depositDomain.bank = depositEntity.bank!!.name
            }
        }

        return depositDomain
    }
}