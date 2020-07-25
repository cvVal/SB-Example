package com.cv.demo.kotapi.model

import javax.persistence.*

@Entity
@Table(name = "DEPOSTITS")
data class Deposit(

        @Id
        @Column(name = "DEPOSIT_ID")
        var depositId: Long? = null,

        @Column(name = "COMPLETED_FLAG")
        var completedFlag: String? = null,

        @OneToOne
        @JoinColumn(name = "BANK_ID", referencedColumnName = "ID")
        var bank: Bank? = null
)