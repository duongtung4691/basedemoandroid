package com.duongtung.newsapp.data.data24h

import com.google.gson.annotations.SerializedName

data class Descrition(
    @SerializedName("content")
    var contents : String,
    @SerializedName("img")
    var imageSource : ImageSource
){
    override fun toString(): String {
        return "Descrition(contents='$contents', imageSource=$imageSource)"
    }
}
