package com.chetanyg.loginexample.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.chetanyg.core.BaseActivity
import com.chetanyg.loginexample.BR
import com.chetanyg.loginexample.R
import com.chetanyg.loginexample.databinding.ActivityLoginBinding
import com.chetanyg.loginexample.ui.home.HomeActivity

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun getViewModel(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun provideLayoutId(): Int = R.layout.activity_login

    override fun getBindingVariable(): Int = BR.login_vm

    override fun setupView(savedInstanceState: Bundle?) {
        initObservables()
    }

    private fun initObservables() {
        viewModel.loginStatus.observe(this, Observer {
            if (it == "Success") {
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show()
            }
        })

    }
}
