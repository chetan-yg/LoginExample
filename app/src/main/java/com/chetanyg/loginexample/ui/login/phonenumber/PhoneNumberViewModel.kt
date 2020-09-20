package com.chetanyg.loginexample.ui.login.phonenumber

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.chetanyg.core.network.ApiCallback
import com.chetanyg.loginexample.R
import com.chetanyg.loginexample.data.model.PhoneNumber
import com.chetanyg.loginexample.data.repository.LoginRepository
import com.chetanyg.loginexample.util.AppConstants
import com.chetanyg.loginexample.util.AppUtil
import retrofit2.Response

class PhoneNumberViewModel(application: Application) : AndroidViewModel(application) {

    var phoneNumberStatus: MutableLiveData<String> = MutableLiveData()

    var phoneNumber: ObservableField<String> = ObservableField("")
    var phoneErrorMessage: ObservableField<String> = ObservableField("")
    var isLoading: ObservableBoolean = ObservableBoolean(false)

    fun validateAndSendOtp() {
        phoneErrorMessage.set(null)
        if (AppUtil.isPhoneValid(phoneNumber.get())) {
            performLogin()
        } else {
            phoneErrorMessage.set(
                getApplication<Application>().resources.getString(
                    R.string.enter_valid_phone_number
                )
            )
        }
    }

    private fun performLogin() {
        loaderDisplay(true)
        LoginRepository.sendOtp(
            phoneNumber.get()!!,
            object : ApiCallback<Response<PhoneNumber>> {
                override fun onFailure(error: Throwable) {
                    loaderDisplay(false)
                    phoneNumberStatus.value = AppConstants.FAILURE
                    phoneErrorMessage.set(
                        getApplication<Application>().resources.getString(
                            R.string.failed_to_send_otp
                        )
                    )
                }

                override fun onSuccess(response: Response<PhoneNumber>) {
                    loaderDisplay(false)
                    if (response.body()?.otpSent!!) {
                        phoneNumberStatus.value = AppConstants.SUCCESS
                    } else {
                        phoneNumberStatus.value = AppConstants.FAILURE
                        phoneErrorMessage.set(response.body()?.errorMessage)
                    }

                }
            })
    }

    private fun loaderDisplay(show: Boolean) {
        isLoading.set(show)
    }
}