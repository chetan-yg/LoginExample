package com.chetanyg.loginexample.ui.login.otp

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.chetanyg.core.network.ApiCallback
import com.chetanyg.loginexample.R
import com.chetanyg.loginexample.data.model.OtpVerification
import com.chetanyg.loginexample.data.repository.LoginRepository
import com.chetanyg.loginexample.util.AppConstants
import com.chetanyg.loginexample.util.AppUtil
import retrofit2.Response

class OtpViewModel(application: Application) : AndroidViewModel(application) {

    var otpStatus: MutableLiveData<String> = MutableLiveData()
    var otp: ObservableField<String> = ObservableField("")
    var otpErrorMessage: ObservableField<String> = ObservableField("")
    var isLoading: ObservableBoolean = ObservableBoolean(false)

    fun validateAndVerifyOtp() {
        otpErrorMessage.set(null)
        if (AppUtil.isFieldValid(otp.get())) {
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
                    otpStatus.value = AppConstants.FAILURE
                    otpErrorMessage.set(
                        getApplication<Application>().resources.getString(
                            R.string.failed_to_verify_otp
                        )
                    )
                }

                override fun onSuccess(response: Response<OtpVerification>) {
                    loaderDisplay(false)
                    if (response.body()?.otpVerified!!) {
                        otpStatus.value = AppConstants.SUCCESS
                    } else {
                        otpStatus.value = AppConstants.FAILURE
                        otpErrorMessage.set(response.body()?.errorMessage)
                    }

                }
            })
    }

    private fun loaderDisplay(show: Boolean) {
        isLoading.set(show)
    }
}