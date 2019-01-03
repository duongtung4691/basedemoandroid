package com.duongtung.newsapp.data.baomoi

import com.duongtung.newsapp.base.repository.data.DataRespond
import com.google.gson.annotations.SerializedName

data class ItemBaoMoi(val id : Int,
                      val date : String,
                      val date_gmt : String,
                      val guid : Rendered,
                      val modified : String,
                      val modified_gmt : String,
                      val slug : String,
                      val status : String,
                      val type : String,
                      val link : String,
                      val title : Rendered,
                      val content : Rendered,
                      val excerpt : Rendered,
                      val author : Int,
                      val categories : List<Int>,
                      @SerializedName("better_featured_image")
                      val image : ImageTitle) : DataRespond()