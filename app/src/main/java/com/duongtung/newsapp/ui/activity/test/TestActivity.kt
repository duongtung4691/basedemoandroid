package com.duongtung.newsapp.ui.activity.test

import android.os.Bundle
import com.duongtung.newsapp.R
import com.duongtung.newsapp.base.BaseActivity
import com.duongtung.newsapp.databinding.ActivityTestBinding
import com.duongtung.newsapp.ui.activity.main.MainViewModel

class TestActivity : BaseActivity<ActivityTestBinding,TestViewModel>() {
    override fun getLayout() = R.layout.activity_test
    override fun getViewMode() = TestViewModel::class.java
    override fun setBindingViewModel() {
        viewModel = TestViewModel()
        viewModel.getName().observe(this, Observer { data ->
            binding.tvTest.text = data
        })
    }
}
