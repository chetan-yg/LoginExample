package com.chetanyg.auth.ui.login.otp

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.chetanyg.auth.R
import com.chetanyg.auth.data.model.OtpVerification
import com.chetanyg.auth.data.repository.LoginRepository
import com.chetanyg.auth.util.AuthConstants
import com.chetanyg.auth.util.AuthUtil
import com.chetanyg.core.network.ApiCallback
import retrofit2.Response

class OtpViewModel(application: Application) : AndroidViewModel(application) {

    var otpStatus: MutableLiveData<String> = MutableLiveData()
    var otp: ObservableField<String> = ObservableField("")
    var otpErrorMessage: ObservableField<String> = ObservableField("")
    var isLoading: ObservableBoolean = ObservableBoolean(false)

    fun validateAndVerifyOtp() {
        otpErrorMessage.set(null)
        if (AuthUtil.isFieldValid(otp.get())) {
            verifyOtp()
        } else {
            otpErrorMessage.set(
                getApplication<Application>().resources.getString(
                    R.string.enter_valid_phone_number
                )
            )
        }
    }


    private fun verifyOtp() {
        loaderDisplay(true)
        LoginRepository.verifyOtp(
            otp.get()!!,
            object : ApiCallback<Response<OtpVerification>> {
                override fun onFailure(error: Throwable) {
                    loaderDisplay(false)
                    otpStatus.value = AuthConstants.FAILURE
                    otpErrorMessage.set(
                        getApplication<Application>().resources.getString(
                            R.string.failed_to_verify_otp
                        )
                    )
                }

                override fun onSuccess(response: Response<OtpVerification>) {
                    loaderDisplay(false)
                    if (response.body()?.otpVerified!!) {
                        otpStatus.value = AuthConstants.SUCCESS
                    } else {
                        otpStatus.value = AuthConstants.FAILURE
                        otpErrorMessage.set(response.body()?.errorMessage)
                    }

                }
            })
    }

    private fun loaderDisplay(show: Boolean) {
        isLoading.set(show)
    }
}