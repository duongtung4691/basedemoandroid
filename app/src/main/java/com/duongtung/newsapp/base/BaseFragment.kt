package com.duongtung.newsapp.base

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duongtung.newsapp.base.ui.base.BaseViewModel

abstract class BaseFragment<VB : ViewDataBinding,VM : BaseViewModel> : Fragment(){
    protected lateinit var binding: VB
    protected lateinit var viewModel: VM


    abstract fun getClassViewMode() : Class<VM>
    @LayoutRes
    abstract fun getLayoutId() : Int
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(getClassViewMode())
    }
}