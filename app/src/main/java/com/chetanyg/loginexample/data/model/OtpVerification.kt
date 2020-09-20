package com.chetanyg.loginexample.data.model

data class OtpVerification(
    val phoneNumber: String,
    val otpVerified: Boolean,
    val errorMessage: String?
) {
}