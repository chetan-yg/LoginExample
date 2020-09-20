package com.chetanyg.loginexample.network

import com.chetanyg.loginexample.data.model.OtpVerification
import com.chetanyg.loginexample.data.model.PhoneNumber
import com.chetanyg.loginexample.data.model.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface EndPoints {
    @FormUrlEncoded
    @POST("auth/login")
    fun login(@Field("email") email: String, @Field("password") password: String): Call<User>

    @FormUrlEncoded
    @POST("auth/sendotp")
    fun sendOTP(@Field("phoneNumber") phoneNumber: String): Call<PhoneNumber>

    @FormUrlEncoded
    @POST("auth/verifyotp")
    fun verifyOTP(@Field("otp") otp: String): Call<OtpVerification>

}