package com.chetanyg.auth.ui.login.otp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.chetanyg.auth.BR
import com.chetanyg.auth.R
import com.chetanyg.auth.databinding.OtpFragmentBinding
import com.chetanyg.auth.util.AuthConstants
import com.chetanyg.core.base.BaseFragment

class OtpFragment : BaseFragment<OtpFragmentBinding, OtpViewModel>() {

    override fun contentLayoutId() = R.layout.otp_fragment

    override fun getBindingVariable() = BR.otpVM

    override fun getViewModel(): Class<OtpViewModel> = OtpViewModel::class.java

    override fun setupView(savedInstanceState: Bundle?) {
        initObservables()
    }

    private fun initObservables() {
        viewModel.otpStatus.observe(this, Observer {
            if (it == AuthConstants.SUCCESS) {
                Toast.makeText(
                    context,
                    getString(R.string.otp_verification_success_le),
                    Toast.LENGTH_SHORT
                )
                    .show()
                val intent =
                    Intent(context, Class.forName("com.chetanyg.loginexample.ui.home.HomeActivity"))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                Toast.makeText(context, getString(R.string.unable_to_login_auth), Toast.LENGTH_LONG)
                    .show()
            }
        })

    }
}