package com.duongtung.newsapp.base.repository.factory

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonToken
import fr.arnaudguyon.xmltojsonlib.XmlToJson
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.StringReader


internal class ConvertToJson<T>(val gson : Gson,val adapter : TypeAdapter<T>) : Converter<ResponseBody, T>{
    override fun convert(value: ResponseBody): T {
//        val inputSource = InputSource(value.charStream())
//        val xmlToJson =  XmlToJson.Builder(value.string())
        val xmlToJson = XmlToJson.Builder(value.string())
            .forceList("rss/channel/item/")
            .build()
        val read = StringReader(xmlToJson.toString())
        val jsonReader = gson.newJsonReader(read)
        val result = adapter.read(jsonReader)
        Log.d("duongtung1111111", xmlToJson.toString())
        if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
            throw JsonIOException("JSON document was not fully consumed.")
        }
        return result
    }

}