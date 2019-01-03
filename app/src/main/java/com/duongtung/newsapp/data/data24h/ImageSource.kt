package com.duongtung.newsapp.data.data24h

import com.google.gson.annotations.SerializedName

data class ImageSource (
    @SerializedName("src")
    var src : String,
    @SerializedName("width")
    var width : Int,
    @SerializedName("height")
    var height : Int
){
    override fun toString(): String {
        return "ImageSource(src='$src', width=$width, height=$height)"
    }
}
