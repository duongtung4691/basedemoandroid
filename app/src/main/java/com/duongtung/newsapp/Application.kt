package com.duongtung.newsapp

import com.duongtung.newsapp.base.BaseApplication
import com.duongtung.newsapp.base.repository.BaseRepository
import com.duongtung.newsapp.data.testxml.CommonUtils
import com.duongtung.newsapp.seviceapi.News24h

class Application : BaseApplication() {
    companion object {
        lateinit var serviceApi: News24h
    }

    override fun onCreate() {
        super.onCreate()
        serviceApi = BaseRepository.instance.getContext(this)!!
            .setBaseUrl(CommonUtils.BASE_URL_BAO_MOI)!!
            .createService(News24h::class.java)
    }
}