package com.duongtung.newsapp.ui.activity.main

import android.accounts.AccountManager
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import android.util.Patterns
import com.duongtung.newsapp.Application
import com.duongtung.newsapp.base.repository.BaseRepository
import com.duongtung.newsapp.base.repository.listener.IServiceRespond
import com.duongtung.newsapp.base.ui.base.BaseViewModel
import com.duongtung.newsapp.base.utils.DataUtilsApplication
import com.duongtung.newsapp.data.actionbar.ActionBar
import com.duongtung.newsapp.data.baomoi.ItemBaoMoi
import com.duongtung.newsapp.data.data24h.BaseRss
import com.duongtung.newsapp.data.testxml.CommonUtils
import com.duongtung.newsapp.seviceapi.News24h
import com.duongtung.newsapp.ui.adapter.ItemAdapter

class MainViewModel : BaseViewModel {
    private var list = MutableLiveData<List<ItemBaoMoi>>()
    private var actionBar = MutableLiveData<ActionBar>()
    private var gmail: String? = null
    private var name= MutableLiveData<String>()
    private var listError = MutableLiveData<String>()
    private var listNews24h =  MutableLiveData<BaseRss>()
    private var api : News24h

    constructor(){
        api = Application.serviceApi
    }
    var adapter = ItemAdapter()

    fun getMyList() : MutableLiveData<List<ItemBaoMoi>> {
        BaseRepository.instance.getResponde(api.getContents(),object : IServiceRespond<List<ItemBaoMoi>>{
            override fun onSuccess(result: List<ItemBaoMoi>) {
                list.postValue(result)
            }

            override fun onError(message: String) {
                listError.postValue(message)
            }
        })
        return list
    }

//    fun getList24h() :  MutableLiveData<BaseRss>{
//        BaseRepository.instance.getRespond(api.getHome24H(), object : IServiceRespond<BaseRss>{
//            override fun onSuccess(result: BaseRss) {
//                listNews24h.postValue(result)
//            }
//
//            override fun onError(message: String) {
//               listError.postValue(message)
//            }
//        })
//        return listNews24h
//    }
    fun getActionBar() : MutableLiveData<ActionBar>{
        actionBar.value = DataUtilsApplication.CreateActionBarHome("Home")
        return actionBar
    }

    fun getAccount(context: Context) :MutableLiveData<String>{
        val gmailPattern = Patterns.EMAIL_ADDRESS // API level 8+
        val accounts = AccountManager.get(context).accounts
        for (account in accounts) {
            Log.d("duongtung66666666666",account.name)
            if (gmailPattern.matcher(account.name).matches()) {
                gmail = account.name
                name.postValue(gmail!!.substring(0, gmail!!.lastIndexOf("@")))
               return name
            }
        }
        return name
    }
}
