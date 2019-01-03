package com.duongtung.newsapp.data.testxml

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class Item{
    @Element(name = "title")
    private val title: String? = null
    @Element(name = "description")
    private val description: String? = null
    @Element(name = "pubDate")
    private val pubDate: String? = null
    @Element(name = "link")
    private val link: String? = null
    @Element(name = "guid")
    private val guid: String? = null

    fun getTitle(): String? {
        return title
    }

    fun getDescription(): String? {
        return description
    }

    fun getPubDate(): String? {
        return pubDate
    }

    fun getLink(): String? {
        return link
    }

    fun getGuid(): String? {
        return guid
    }


}