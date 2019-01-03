package com.duongtung.newsapp.data.data24h

import com.google.gson.annotations.SerializedName

data class ItemNew (
    @SerializedName("link")
    var link: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("guid")
    var guid: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("pubDate")
    var date: String){

    override fun toString(): String {
        return "ItemNew(link='$link', description='$description', guid='$guid', title='$title', date='$date')"
    }
}