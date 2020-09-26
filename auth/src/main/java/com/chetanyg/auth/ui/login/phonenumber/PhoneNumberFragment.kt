package com.chetanyg.auth.ui.login.phonenumber

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.chetanyg.auth.BR
import com.chetanyg.auth.R
import com.chetanyg.auth.databinding.PhoneNumberFragmentBinding
import com.chetanyg.auth.util.AuthConstants
import com.chetanyg.core.base.BaseFragment

class PhoneNumberFragment : BaseFragment<PhoneNumberFragmentBinding, PhoneNumberViewModel>() {

    override fun contentLayoutId() = R.layout.phone_number_fragment

    override fun getBindingVariable() = BR.phoneNumberVM

    override fun getViewModel(): Class<PhoneNumberViewModel> = PhoneNumberViewModel::class.java

    override fun setupView(savedInstanceState: Bundle?) {
        initObservables()
    }

    private fun initObservables() {
        viewModel.phoneNumberStatus.observe(this, Observer {
            if (it == AuthConstants.SUCCESS) {
                findNavController().navigate(R.id.action_phoneNumberFragment_to_otpFragment)
            } else {
                Toast.makeText(context, getString(R.string.unable_to_login_auth), Toast.LENGTH_LONG)
                    .show()
            }
        })

    }

}