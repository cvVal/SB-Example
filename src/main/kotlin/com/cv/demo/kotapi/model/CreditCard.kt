package com.cv.demo.kotapi.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "TX_HIST")
data class CreditCard(

        @Id
        @Column(name = "TX_ID")
        var transactionId: String? = null,

        @Column(name = "TX_CONF_NUMBER")
        var confirmationNo: String? = null,

        @Column(name = "CARD_KEY_ID")
        var cardId: String? = null,

        @Column(name = "TX_DATE")
        var paymentDate: Date? = null,

        @Column(name = "TX_RESULT")
        var paymentStatus: String? = null,

        @Column(name = "TX_REJECT_REASON")
        var rejectReason: String? = null,

        @Column(name = "TX_AMOUNT")
        var amount: String? = null,

        @Column(name = "LAST_4_DIGITS")
        var reference: String? = null,

        @Column(name = "TX_TYPE")
        var type: String? = null,

        @Column(name = "PATIENT_ID")
        var patientId: String? = null
)