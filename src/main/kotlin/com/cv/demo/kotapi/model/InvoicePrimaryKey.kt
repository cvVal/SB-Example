package com.cv.demo.kotapi.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class InvoicePrimaryKey(

        @Column(name = "INVOICE_ID")
        var invoiceId: String? = null,

        @Column(name = "INVOICE_SEQ")
        var invoiceSeq: String? = null
) : Serializable {

    companion object {
        private const val serialVersionUID = 6899628261147931930L
    }
}
