package com.chetanyg.loginexample.ui.login.otp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.chetanyg.core.base.BaseFragment
import com.chetanyg.loginexample.BR
import com.chetanyg.loginexample.R
import com.chetanyg.loginexample.databinding.OtpFragmentBinding
import com.chetanyg.loginexample.ui.home.HomeActivity
import com.chetanyg.loginexample.util.AppConstants

class OtpFragment : BaseFragment<OtpFragmentBinding, OtpViewModel>() {

    override fun contentLayoutId() = R.layout.otp_fragment

    override fun getBindingVariable() = BR.otpVM

    override fun getViewModel(): Class<OtpViewModel> = OtpViewModel::class.java

    override fun setupView(savedInstanceState: Bundle?) {
        initObservables()

    }

    private fun initObservables() {
        viewModel.otpStatus.observe(this, Observer {
            if (it == AppConstants.SUCCESS) {
                Toast.makeText(
                    context,
                    getString(R.string.otp_verification_success_le),
                    Toast.LENGTH_SHORT
                )
                    .show()
                val intent = Intent(context, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            } else {
                Toast.makeText(context, getString(R.string.unable_to_login_auth), Toast.LENGTH_LONG)
                    .show()
            }
        })

    }
}