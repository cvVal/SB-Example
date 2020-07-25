package com.cv.demo.kotapi.constants

object Constants {

    const val PORC = "P"

    const val DATE_FORMAT = "yyyy-MM-dd"
    const val TIME_FORMAT = "HH:mm:ss"
    const val DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val WRONG_FORMAT = "is an incorrect date, pattern must match with yyyy-MM-dd."
    const val NULL_NUMBER_OF_DAYS = "Number of days to add cannot be null."
    const val EMPTY_MAX_DAY = "Max days range cannot be null or empty."
    const val WRONG_RANGE_1 = "Start Date cannot be greater than End Date."
    const val WRONG_RANGE_2 = "Start Date cannot be empty when End Date is not empty."
    const val WRONG_RANGE_3 = "End Date cannot be empty when Start Date is not empty."
    const val WRONG_RANGE_4 = "Days range cannot be greater than 720 days."

    const val FAILURE_ON_CALL_OPERATION = "PaymentRemoteServiceImpl.getSale: Failure on calling Payment sale operation."
    const val ERROR_TRYING_TO_CALL_PAYMENT_AGENT_API = "Error trying to call Payment Agent API - "
    const val UNKNOWN_ERROR_TRYING_TO_CALL_PAYMENT_AGENT = "Unknown error trying to call Payment Agent API"
    const val HEALTH_MESSAGE = "message"

    const val CHECK_PAYMENT = "Check"
    const val C_CARD_PAYMENT = "CreditCard"
    const val SUCCESS_STATUS = "SUCCESS"
    const val PROGRESS_STATUS = "IN PROGRESS"
}