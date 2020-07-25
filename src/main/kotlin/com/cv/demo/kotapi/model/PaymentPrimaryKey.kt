package com.cv.demo.kotapi.model

import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class PaymentPrimaryKey(

        @Column(name = "PORC")
        var porC: String? = null,

        @Column(name = "PAYOR_ID")
        var patientId: String? = null,

        @Column(name = "CHECK_NO")
        var checkNo: String? = null,

        @Column(name = "PAYMENT_DATE")
        var paymentDate: Date? = null
) : Serializable {

    companion object {
        private const val serialVersionUID = 547495375069830966L
    }
}