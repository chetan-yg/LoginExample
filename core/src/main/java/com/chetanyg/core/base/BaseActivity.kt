package com.chetanyg.core.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<T : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: T
    protected lateinit var context: Context

    /**
     * @return view model instance
     * Implement this function in your activity and pass View model class of your activity
     */
    abstract fun getViewModel(): Class<VM>

    /**
     * @return layout id for particular activity
     * Implement this function in your activity and pass layout resource id
     */
    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    /**
     * @return variable id
     * gets the view model from implemented fragment
     */
    protected abstract fun getBindingVariable(): Int

    /**
     * @return savedInstanceState
     * Use this function as onCreate()
     */
    protected abstract fun setupView(savedInstanceState: Bundle?)

    /**
     * common onCreate method for all activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, provideLayoutId())
        binding.lifecycleOwner = this
        context = this
        viewModel = ViewModelProvider(this).get(getViewModel())
        binding.setVariable(getBindingVariable(), viewModel)
        setupView(savedInstanceState)
    }

}