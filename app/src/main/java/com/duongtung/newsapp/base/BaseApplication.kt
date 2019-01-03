package com.duongtung.newsapp.base

import android.app.Application
import com.duongtung.newsapp.base.repository.BaseRepository
import com.duongtung.newsapp.base.resource.BaseResource

abstract class BaseApplication : Application(){

    companion object {
        private var resource: BaseResource? = null

        fun getResources(): BaseResource {

            return resource!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        resource = BaseResource(context = applicationContext)
    }
}
