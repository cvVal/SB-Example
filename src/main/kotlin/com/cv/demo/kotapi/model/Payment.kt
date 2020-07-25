package com.cv.demo.kotapi.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "PAYMENTS")
data class Payment(

        @Column(name = "PAYMENT_AMT")
        var amount: String? = null,

        @Column(name = "DEPOSIT_ID", insertable = false, updatable = false)
        var depositId: Long? = null,

        @EmbeddedId
        var paymentPrimaryKey: PaymentPrimaryKey? = null,

        @JoinColumn(
                name = "DEPOSIT_ID",
                referencedColumnName = "DEPOSIT_ID",
                insertable = false,
                updatable = false
        )
        @OneToMany
        var creditCard: List<CreditCard>? = null,

        @OneToOne
        @JoinColumn(name = "DEPOSIT_ID")
        var deposit: Deposit? = null,

        @OneToMany(mappedBy = "payment")
        var invoicePayment: List<InvoicePayment>? = null
) : Serializable {

    companion object {
        private const val serialVersionUID = 4177993567819044597L
    }
}