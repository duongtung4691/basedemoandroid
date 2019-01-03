package com.duongtung.newsapp.base.repository.listener

interface IServiceRespond <T> {
    fun onSuccess(result : T)
    fun onError(message: String)
}
