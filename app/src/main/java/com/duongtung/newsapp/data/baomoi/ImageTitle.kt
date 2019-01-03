package com.duongtung.newsapp.data.baomoi

import com.google.gson.annotations.SerializedName

data class ImageTitle(var id : Int,
                      @SerializedName("alt_text")
                      var altText : String,
                      @SerializedName("caption")
                      var caption : String,
                      @SerializedName("media_type")
                      var mediaType: String,
                      @SerializedName("media_details")
                      var mediaDetails : MediaDetails,
                      @SerializedName("post")
                      var post : String,
                      @SerializedName("source_url")
                      var url : String
)