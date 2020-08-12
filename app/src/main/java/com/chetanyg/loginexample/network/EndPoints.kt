package com.chetanyg.loginexample.network

import com.chetanyg.loginexample.data.model.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface EndPoints {
    @FormUrlEncoded
    @POST("auth/login")
    fun LOGIN(@Field("email") email: String, @Field("password") password: String): Call<User>


}