package com.cv.demo.kotapi.repository

import com.cv.demo.kotapi.model.Patient
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PatientRepo : CrudRepository<Patient, String> {

    fun findByPatientId(patientId: String?): Patient?

}