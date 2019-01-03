package com.duongtung.newsapp.data.data24h

import com.duongtung.newsapp.base.repository.data.DataRespond
import com.google.gson.annotations.SerializedName

data class BaseRss( @SerializedName("rss") var rss: Rss) : DataRespond()
