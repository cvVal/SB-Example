package com.cv.demo.kotapi.model

import javax.persistence.*

@Entity
@Table(name = "INVOICES_TABLE")
data class Invoice(

        @Column(name = "PORC")
        var porc: String? = null,

        @Column(name = "PAYOR_ID")
        var payorId: Int? = null,

        @Column(name = "PATIENT_ID")
        var patientId: String? = null,

        @OneToMany(mappedBy = "invoice")
        var invoicePayment: List<InvoicePayment>? = null,

        @EmbeddedId
        var invoicePrimaryKey: InvoicePrimaryKey? = null
)