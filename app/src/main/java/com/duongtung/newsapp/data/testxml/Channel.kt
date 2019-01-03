package com.duongtung.newsapp.data.testxml

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
class Channel {
    @Element(name = "title")
    val title: String? = null
    @ElementList(entry = "item", inline = true)
    val array: List<Item>? = null
}