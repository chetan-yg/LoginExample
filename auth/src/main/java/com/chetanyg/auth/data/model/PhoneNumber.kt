package com.chetanyg.auth.data.model

data class PhoneNumber(val phoneNumber: String, val otpSent: Boolean, val errorMessage: String?) {
}