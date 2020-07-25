package com.cv.demo.kotapi.domain

class DemoGetResponse {

    var patientId: String? = null
    var payments: List<Payments>? = null

    class Payments {

        var paymentType: String? = null
        var checkNo: String? = null
        var transactionId: String? = null
        var confirmationNo: String? = null
        var cardId: String? = null
        var paymentDate: String? = null
        var paymentTime: String? = null
        var paymentStatus: String? = null
        var rejectReason: String? = null
        var amount: String? = null
        var reference: String? = null
        var type: String? = null
        var deposit: Deposit? = null
        var invoices: List<Invoice>? = null

    }

    class Deposit {

        var depositId: String? = null
        var bank: String? = null
    }

    class Invoice {

        var invoiceId: String? = null
    }

}
