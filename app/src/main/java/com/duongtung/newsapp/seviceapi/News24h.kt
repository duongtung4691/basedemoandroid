package com.duongtung.newsapp.seviceapi

import com.duongtung.newsapp.base.repository.factory.XmlOrJsonConverterFactory
import com.duongtung.newsapp.data.baomoi.ItemBaoMoi
import com.duongtung.newsapp.data.data24h.BaseRss
import com.duongtung.newsapp.data.testxml.CommonUtils
import io.reactivex.Observable
import retrofit2.http.GET

interface News24h{
    @XmlOrJsonConverterFactory.Xml
    @GET(value = "${CommonUtils.BASE_URL_24H}${CommonUtils.TIN_TUC}")
    fun getHome24H():Observable<BaseRss>

    @XmlOrJsonConverterFactory.Xml
    @GET("${CommonUtils.BASE_URL_24H}${CommonUtils.TIN_TUC}")
    fun getTinTuc():Observable<BaseRss>

    @XmlOrJsonConverterFactory.Xml
    @GET("${CommonUtils.BASE_URL_24H}${CommonUtils.AN_NINH_HINH_SU}")
    fun getAnNinhHinhSu():Observable<BaseRss>

    @XmlOrJsonConverterFactory.Xml
    @GET("${CommonUtils.BASE_URL_24H}${CommonUtils.TIN_TUC_QUOC_TE}")
    fun getTinTucQuocTe():Observable<BaseRss>

    @XmlOrJsonConverterFactory.Json
    @GET(CommonUtils.POSTS)
    fun getContents() : Observable<List<ItemBaoMoi>>
}