package com.chetanyg.loginexample.data.repository

import com.chetanyg.core.network.ApiCallback
import com.chetanyg.core.network.ApiServiceClient
import com.chetanyg.loginexample.data.model.OtpVerification
import com.chetanyg.loginexample.data.model.PhoneNumber
import com.chetanyg.loginexample.data.model.User
import com.chetanyg.loginexample.network.EndPoints
import retrofit2.Call
import retrofit2.Response


object LoginRepository {
    private const val TAG: String = "LoginRepository"

    fun login(email: String, password: String, onLoginResponse: OnLoginResponse) {
        ApiServiceClient.client.create(
            EndPoints::class.java
        ).login(
            email = email, password = password
        ).enqueue(object : retrofit2.Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>?) {
                onLoginResponse.onSuccess(response)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                onLoginResponse.onFailure(t)
            }
        })

    }

    fun sendOtp(phoneNumber: String, apiCallback: ApiCallback<Response<PhoneNumber>>) {
        ApiServiceClient.client.create(
            EndPoints::class.java
        ).sendOTP(
            phoneNumber = phoneNumber
        ).enqueue(object : retrofit2.Callback<PhoneNumber> {
            override fun onResponse(call: Call<PhoneNumber>, response: Response<PhoneNumber>?) {

                if (response != null) {
                    if (response.isSuccessful) {
                        apiCallback.onSuccess(response)
                    } else {
                        apiCallback.onFailure(Throwable(response.errorBody().toString()))
                    }
                }
            }

            override fun onFailure(call: Call<PhoneNumber>, t: Throwable) {
                apiCallback.onFailure(t)
            }
        })

    }

    fun verifyOtp(otp: String, apiCallback: ApiCallback<Response<OtpVerification>>) {
        ApiServiceClient.client.create(
            EndPoints::class.java
        ).verifyOTP(
            otp = otp
        ).enqueue(object : retrofit2.Callback<OtpVerification> {
            override fun onResponse(
                call: Call<OtpVerification>,
                response: Response<OtpVerification>?
            ) {

                if (response != null) {
                    if (response.isSuccessful) {
                        apiCallback.onSuccess(response)
                    } else {
                        apiCallback.onFailure(Throwable(response.errorBody().toString()))
                    }
                }
            }

            override fun onFailure(call: Call<OtpVerification>, t: Throwable) {
                apiCallback.onFailure(t)
            }
        })

    }

    interface OnLoginResponse {
        fun onSuccess(response: Response<User>?)
        fun onFailure(t: Throwable)
    }

}