package com.cv.demo.kotapi.validator

import com.cv.demo.kotapi.exceptions.BadRequestException

object ValidateInvoices {

    fun validateInvoice(invoice: String, invoiceList: ArrayList<String>) {

        if (invoice.matches("\\d+".toRegex()))
            if (invoice.matches("\\d{0,10}".toRegex()))
                invoiceList.add(invoice)
            else
                throw BadRequestException("invoiceId length must be between 0 and 10")
        else
            throw BadRequestException("invoiceId must be number")
    }
}