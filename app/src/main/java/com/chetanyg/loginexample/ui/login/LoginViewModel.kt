package com.chetanyg.loginexample.ui.login

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.chetanyg.loginexample.R
import com.chetanyg.loginexample.data.model.User
import com.chetanyg.loginexample.data.repository.LoginRepository
import com.chetanyg.loginexample.util.AppUtil
import retrofit2.Response

private const val TAG: String = "LoginViewModel"

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var loginStatus: MutableLiveData<String> = MutableLiveData()

    var userName: ObservableField<String> = ObservableField("")
    var password: ObservableField<String> = ObservableField("")
    var emailErrorMessage: ObservableField<String> = ObservableField("")
    var passwordErrorMessage: ObservableField<String> = ObservableField("")
    var isLoading: ObservableBoolean = ObservableBoolean(false)


    fun validateAndLogin() {
        if (AppUtil.isEmailValid(userName.get()) && AppUtil.isFieldValid(password.get())) {
            performLogin()
        } else {
            if (!AppUtil.isEmailValid(userName.get())) emailErrorMessage.set(
                getApplication<Application>().resources.getString(
                    R.string.enter_valid_username_auth
                )
            ) else emailErrorMessage.set(null)
            if (!AppUtil.isFieldValid(password.get())) passwordErrorMessage.set(
                getApplication<Application>().resources.getString(
                    R.string.enter_valid_password_auth
                )
            ) else passwordErrorMessage.set(
                null
            )
        }
    }

    private fun performLogin() {

        isLoading.set(true)

        LoginRepository.login(
            userName.get()!!,
            password.get()!!,
            object : LoginRepository.OnLoginResponse {
                override fun onSuccess(response: Response<User>?) {
                    isLoading.set(false)
                    loginStatus.value = "Success"
                }

                override fun onFailure(t: Throwable) {
                    isLoading.set(false)
                    loginStatus.value = "Failed"
                }
            })
    }
}