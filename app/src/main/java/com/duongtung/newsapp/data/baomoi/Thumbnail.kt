package com.duongtung.newsapp.data.baomoi

import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("file")
    var name : String,
    @SerializedName("width")
    var width : Int,
    @SerializedName("height")
    var height : Int,
    @SerializedName("mime_type")
    var mimeType : String,
    @SerializedName("source_url")
    var sourceUrl: String
)
