package com.chetanyg.loginexample.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.chetanyg.auth.ui.login.LoginActivity
import com.chetanyg.core.base.BaseActivity
import com.chetanyg.loginexample.BR
import com.chetanyg.loginexample.R
import com.chetanyg.loginexample.databinding.ActivitySplashScreenBinding
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding, SplashScreenViewModel>() {

    override fun getViewModel(): Class<SplashScreenViewModel> = SplashScreenViewModel::class.java

    override fun provideLayoutId(): Int = R.layout.activity_splash_screen

    override fun getBindingVariable(): Int = BR.splash_vm

    override fun setupView(savedInstanceState: Bundle?) {
        navigateInside()
    }

    private fun navigateInside() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                asa_img_logo as View,
                getString(R.string.logo_transition)
            )
            startActivity(intent, options.toBundle())
        }, 1000)
    }
}
