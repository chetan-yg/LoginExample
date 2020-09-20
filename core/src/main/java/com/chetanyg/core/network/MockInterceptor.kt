package com.chetanyg.core.network

import android.util.Log
import com.chetanyg.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url.toUri().toString()
            Log.d("MockInterceptor", "uri " + uri)
            val responseString = when {
                uri.endsWith("sendotp") -> sendOTPResponse
                uri.endsWith("verifyotp") -> verifyOTPResponse
                else -> ""
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    responseString.toByteArray()
                        .toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }

}

const val sendOTPResponse = "{\n" +
        "  \"phoneNumber\": \"9480938618\",\n" +
        "  \"otpSent\": true,\n" +
        "  \"errorMessage\": null\n" +
        "}"

const val verifyOTPResponse = "{\n" +
        "  \"phoneNumber\": \"9480938618\",\n" +
        "  \"otpVerified\": true,\n" +
        "  \"errorMessage\": null\n" +
        "}"