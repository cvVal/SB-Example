package com.cv.demo.kotapi.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "PATIENTS_TABLE")
data class Patient(

        @Id
        @Column(name = "ID")
        var patientId: String? = null
)
