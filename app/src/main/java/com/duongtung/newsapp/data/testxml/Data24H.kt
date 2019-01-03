package com.duongtung.newsapp.data.testxml

import com.duongtung.newsapp.base.repository.data.DataRespond
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "rss", strict = false)
class Data24H : DataRespond(){
    @Element
    var channel: Channel
        set(value) {
            channel = value
        }
    get() = channel

}