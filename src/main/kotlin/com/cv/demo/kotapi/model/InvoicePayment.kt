package com.cv.demo.kotapi.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "INVOICE_PAYMENTS")
data class InvoicePayment(

    @Id
    @Column(name = "UNIQ_ID")
    var uniqId: Long = 0,

    @Column(name = "INVOICE_ID")
    var invoiceId: String? = null,

    @Column(name = "INVOICE_SEQ")
    var invoiceSeq: String? = null,

    @Column(name = "PAYMENT_DATE", insertable = false, updatable = false)
    var paymentDate: Date? = null,

    @Column(name = "PAYOR_ID", insertable = false, updatable = false)
    var payorId: String? = null,

    @Column(name = "PORC", insertable = false, updatable = false)
    var porC: String? = null,

    @JoinColumns(
            JoinColumn(
                    name = "INVOICE_ID",
                    referencedColumnName = "INVOICE_ID",
                    insertable = false,
                    updatable = false
            ),
            JoinColumn(
                    name = "INVOICE_SEQ",
                    referencedColumnName = "INVOICE_SEQ",
                    insertable = false,
                    updatable = false
            )
    )
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    var invoice: Invoice? = null,

    @MapsId(value = "paymentPrimaryKey")
    @JoinColumns(
            JoinColumn(name = "PORC", referencedColumnName = "PORC"),
            JoinColumn(name = "PAYOR_ID", referencedColumnName = "PAYOR_ID"),
            JoinColumn(name = "CHECK_NO", referencedColumnName = "CHECK_NO"),
            JoinColumn(name = "PAYMENT_DATE", referencedColumnName = "PAYMENT_DATE")
    )
    @ManyToOne(optional = true)
    var payment: Payment? = null
)