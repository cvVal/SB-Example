package com.cv.demo.kotapi.service

import com.cv.demo.kotapi.model.Patient

interface PatientService {

    @Throws(Exception::class)
    fun getPatientById(patientId: String): Patient?
}