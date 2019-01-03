package com.duongtung.newsapp.base.repository

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.duongtung.newsapp.base.repository.factory.JsonAndXmlConverters
import com.duongtung.newsapp.base.repository.data.DataRespond
import com.duongtung.newsapp.base.repository.factory.XmlOrJsonConverterFactory
import com.duongtung.newsapp.base.repository.listener.IServiceRespond
import com.duongtung.newsapp.base.repository.utils.PrefUtils
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BaseRepository {
    private var mContext: Context? = null

    fun getContext(context: Context): BaseRepository? {
        mContext = context
        return repository
    }

    fun setBaseUrl(baseUrl: String): BaseRepository? {
        if (retrofit == null) {
            if (okHttpClient == null)
                initOkHttp(mContext)
            //            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
            retrofit = Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient!!)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(XmlOrJsonConverterFactory(GsonConverterFactory.create(), JsonAndXmlConverters.create()))
                .build()
        }
        return repository
    }

    fun <T> createService(service: Class<T>): T {
        return retrofit!!.create(service)
    }

    fun <T : DataRespond> getRespondSingle(completable: Single<T>, callback: IServiceRespond<T>) {
        disposable.add(completable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<T>() {
                override fun onSuccess(t: T) {
                    if (callback != null) {
                        callback!!.onSuccess(t)
                    }
                }

                override fun onError(e: Throwable) {
                    if (callback != null) {
                        callback!!.onError(e.message!!)
                    }
                }
            })
        )
    }

    fun <T : DataRespond> getRespond(completable: Observable<T>, callback: IServiceRespond<T>) {
        disposable.add(completable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<T>() {
                override fun onNext(t: T) {
                    if (callback != null) callback!!.onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    if (callback != null) callback!!.onError(e.message!!)
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete: ")
                }
            })
        )
    }


    fun <T> getResponde(completable: Observable<T>, callback: IServiceRespond<T>) {
        disposable.add(completable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<T>() {
                override fun onNext(t: T) {
                    if (callback != null) callback!!.onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    if (callback != null) callback!!.onError(e.message!!)
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete: ")
                }
            })
        )
    }
    companion object {
        private var retrofit: Retrofit? = null
        private var repository: BaseRepository? = null
        private const val REQUEST_TIMEOUT = 60
        private var okHttpClient: OkHttpClient? = null
        private val disposable = CompositeDisposable()
        private val TAG = BaseRepository::class.java.simpleName

        val instance: BaseRepository
            get() {
                if (repository == null) repository = BaseRepository()
                return repository!!
            }

        private fun initOkHttp(context: Context?) {
            val httpClient = OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            httpClient.addInterceptor(interceptor)
            // Content-Type: application/rss+xml; charset=UTF-8
            //Accept: text/html,application/xhtml+xml,application/xml
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")

                // Adding Authorization token (API Key)
                // Requests will be denied without API key
                if (!TextUtils.isEmpty(PrefUtils.getApiKey(context))) {
                    requestBuilder.addHeader("Authorization", PrefUtils.getApiKey(context))
                }

                val request = requestBuilder.build()
                chain.proceed(request)
            }

            okHttpClient = httpClient.build()
        }
    }
}
