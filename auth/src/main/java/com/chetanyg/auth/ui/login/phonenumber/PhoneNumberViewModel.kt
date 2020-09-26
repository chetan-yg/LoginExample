package com.chetanyg.auth.ui.login.phonenumber

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.chetanyg.auth.R
import com.chetanyg.auth.data.model.PhoneNumber
import com.chetanyg.auth.data.repository.LoginRepository
import com.chetanyg.auth.util.AuthConstants
import com.chetanyg.auth.util.AuthUtil
import com.chetanyg.core.network.ApiCallback
import retrofit2.Response

class PhoneNumberViewModel(application: Application) : AndroidViewModel(application) {

    var phoneNumberStatus: MutableLiveData<String> = MutableLiveData()

    var phoneNumber: ObservableField<String> = ObservableField("")
    var phoneErrorMessage: ObservableField<String> = ObservableField("")
    var isLoading: ObservableBoolean = ObservableBoolean(false)

    fun validateAndSendOtp() {
        phoneErrorMessage.set(null)
        if (AuthUtil.isPhoneValid(phoneNumber.get())) {
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
                    phoneNumberStatus.value = AuthConstants.FAILURE
                    phoneErrorMessage.set(
                        getApplication<Application>().resources.getString(
                            R.string.failed_to_send_otp
                        )
                    )
                }

                override fun onSuccess(response: Response<PhoneNumber>) {
                    loaderDisplay(false)
                    if (response.body()?.otpSent!!) {
                        phoneNumberStatus.value = AuthConstants.SUCCESS
                    } else {
                        phoneNumberStatus.value = AuthConstants.FAILURE
                        phoneErrorMessage.set(response.body()?.errorMessage)
                    }

                }
            })
    }

    private fun loaderDisplay(show: Boolean) {
        isLoading.set(show)
    }
}