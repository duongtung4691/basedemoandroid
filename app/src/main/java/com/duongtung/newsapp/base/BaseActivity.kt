package com.duongtung.newsapp.base

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModelProviders
import android.database.DatabaseUtils
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.duongtung.newsapp.base.ui.base.BaseViewModel


abstract class BaseActivity<VB : ViewDataBinding,VM : BaseViewModel> : AppCompatActivity(){
    protected lateinit var viewModel : VM
    protected lateinit var binding : VB

    abstract fun getViewMode() : Class<VM>
    abstract fun getLayout() : Int
    abstract fun setBindingViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,getLayout())
        viewModel = ViewModelProviders.of(this).get(getViewMode())
        setBindingViewModel()
    }

}