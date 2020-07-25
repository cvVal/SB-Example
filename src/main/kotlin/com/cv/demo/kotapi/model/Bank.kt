package com.cv.demo.kotapi.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "BANKS")
data class Bank(

        @Id
        @Column(name = "ID")
        var id: Long? = 0,

        @Column(name = "NAME")
        var name: String? = null
)