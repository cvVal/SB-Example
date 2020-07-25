package com.cv.demo.kotapi.validator

import com.cv.demo.kotapi.constants.Constants.DATE_FORMAT
import com.cv.demo.kotapi.constants.Constants.EMPTY_MAX_DAY
import com.cv.demo.kotapi.constants.Constants.NULL_NUMBER_OF_DAYS
import com.cv.demo.kotapi.constants.Constants.WRONG_FORMAT
import com.cv.demo.kotapi.constants.Constants.WRONG_RANGE_1
import com.cv.demo.kotapi.constants.Constants.WRONG_RANGE_2
import com.cv.demo.kotapi.constants.Constants.WRONG_RANGE_3
import com.cv.demo.kotapi.constants.Constants.WRONG_RANGE_4
import com.cv.demo.kotapi.exceptions.BadRequestException
import org.joda.time.Days
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat

object ValidateDates {

    fun addDaysToDate(date: LocalDate, numberOfDays: Int?): LocalDate {

        val addition = date.plusDays(numberOfDays?.let { it } ?: throw BadRequestException(NULL_NUMBER_OF_DAYS))

        return addition
    }

    fun validateDates(startDate: String?, endDate: String?, maxDaysRange: String?) {

        val dateTimeFormatter = DateTimeFormat.forPattern(DATE_FORMAT)

        when {
            startDate != null && endDate != null -> {

                val startDateValue: LocalDate
                val endDateValue: LocalDate

                try {
                    startDateValue = LocalDate.parse(startDate, dateTimeFormatter)
                } catch (e: Exception) {
                    throw BadRequestException("$startDate $WRONG_FORMAT")
                }

                try {
                    endDateValue = LocalDate.parse(endDate, dateTimeFormatter)
                } catch (e: Exception) {
                    throw BadRequestException("$endDate, $WRONG_FORMAT")
                }

                if (startDateValue.isAfter(endDateValue))
                    throw BadRequestException(WRONG_RANGE_1)

                val days = Days.daysBetween(startDateValue, endDateValue).days

                if (!maxDaysRange.isNullOrEmpty()) {
                    if (days > maxDaysRange.toInt())
                        throw BadRequestException(WRONG_RANGE_4)
                } else {
                    throw BadRequestException(EMPTY_MAX_DAY)
                }
            }
            startDate == null && endDate != null -> throw BadRequestException(WRONG_RANGE_2)
            startDate != null && endDate == null -> throw BadRequestException(WRONG_RANGE_3)
        }
    }
}