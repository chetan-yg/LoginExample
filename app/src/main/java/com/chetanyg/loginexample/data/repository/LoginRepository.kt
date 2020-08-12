package com.chetanyg.loginexample.data.repository

import com.chetanyg.core.network.ApiServiceClient
import com.chetanyg.loginexample.data.model.User
import com.chetanyg.loginexample.network.EndPoints
import retrofit2.Call
import retrofit2.Response


object LoginRepository {
    private const val TAG: String = "LoginRepository"

    fun login(email: String, password: String, onLoginResponse: OnLoginResponse) {
        ApiServiceClient.client.create(
            EndPoints::class.java
        ).LOGIN(
            email = email
            , password = password
        ).enqueue(object : retrofit2.Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>?) {
                onLoginResponse.onSuccess(response)
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                onLoginResponse.onFailure(t)
            }
        })

    }

    interface OnLoginResponse {
        fun onSuccess(response: Response<User>?)
        fun onFailure(t: Throwable)
    }

}