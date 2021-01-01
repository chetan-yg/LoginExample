package com.chetanyg.loginexample.util


class AppUtil {
    companion object {
        fun isEmailValid(email: String?): Boolean {
            if (email.isNullOrEmpty()) return false
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isPhoneValid(phoneNumber: String?): Boolean {
            if (phoneNumber.isNullOrEmpty()) return false
            return android.util.Patterns.PHONE.matcher(phoneNumber).matches()
        }

        fun isFieldValid(fieldText: String?): Boolean {
            return !fieldText.isNullOrEmpty()
        }
    }
}
