package com.duongtung.newsapp.ui.activity.test

import android.arch.lifecycle.MutableLiveData
import com.duongtung.newsapp.base.ui.base.BaseViewModel

class TestViewModel : BaseViewModel{
    private var name= MutableLiveData<String>()
    constructor(){
        name.postValue("aaaaaaaa")
    }

    fun getName(): MutableLiveData<String>{
        return name;
    }
}

