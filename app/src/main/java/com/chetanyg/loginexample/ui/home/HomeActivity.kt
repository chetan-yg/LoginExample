package com.chetanyg.loginexample.ui.home

import android.os.Bundle
import com.chetanyg.core.base.BaseActivity
import com.chetanyg.loginexample.BR
import com.chetanyg.loginexample.R
import com.chetanyg.loginexample.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun provideLayoutId(): Int = R.layout.activity_home

    override fun getBindingVariable(): Int = BR.home_vm

    override fun setupView(savedInstanceState: Bundle?) {

    }
}