package com.chetanyg.auth.ui.login

import android.os.Bundle
import com.chetanyg.auth.BR
import com.chetanyg.auth.R
import com.chetanyg.auth.databinding.ActivityLoginBinding
import com.chetanyg.core.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun getViewModel(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun provideLayoutId(): Int = R.layout.activity_login

    override fun getBindingVariable(): Int = BR.login_vm

    override fun setupView(savedInstanceState: Bundle?) {}
}
