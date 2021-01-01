package com.chetanyg.auth.data.model

data class OtpVerification(
    val phoneNumber: String,
    val otpVerified: Boolean,
    val errorMessage: String?
) {
}