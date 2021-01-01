package com.chetanyg.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<T : ViewDataBinding, VM : ViewModel> : Fragment() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: T

    /**
     * @return view model instance
     * Implement this function in your activity and pass View model class of your activity
     */
    abstract fun getViewModel(): Class<VM>

    /**
     * @return variable layout id
     * gets the layout id from implemented fragment
     */
    @LayoutRes
    protected abstract fun contentLayoutId(): Int

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
     * common onCreateView method for all fragments
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, contentLayoutId(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel = ViewModelProvider(this).get(getViewModel())


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(getBindingVariable(), viewModel)
        setupView(savedInstanceState)
    }
}