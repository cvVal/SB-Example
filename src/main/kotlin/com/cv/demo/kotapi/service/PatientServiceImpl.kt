package com.cv.demo.kotapi.service

import com.cv.demo.kotapi.exceptions.BusinessException
import com.cv.demo.kotapi.model.Patient
import com.cv.demo.kotapi.repository.PatientRepo
import org.springframework.stereotype.Service

@Service
class PatientServiceImpl(val patientRepo: PatientRepo?) : PatientService {

    @Throws(BusinessException::class)
    override fun getPatientById(patientId: String): Patient? = patientRepo?.findByPatientId(patientId) ?: throw BusinessException("Patient does not exists")
}