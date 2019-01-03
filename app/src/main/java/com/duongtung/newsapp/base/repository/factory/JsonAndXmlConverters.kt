package com.duongtung.newsapp.base.repository.factory

import android.support.annotation.Nullable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Both the Gson converter and the Simple Framework converter accept all types. Because of this,
 * you cannot use both in a single service by default. In order to work around this, we can create
 * an @Json and @Xml annotation to declare which serialization format each endpoint should use and
 * then write our own Converter.Factory which delegates to either the Gson or Simple Framework
 * converter.
 */


class JsonAndXmlConverters(private val gson: Gson) : Converter.Factory() {


    @Nullable
    override fun responseBodyConverter(
        type: Type?,
        annotations: Array<Annotation>?,
        retrofit: Retrofit?
    ): Converter<ResponseBody, *>? {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return ConvertToJson(gson, adapter)
    }


    companion object {
        /**
         * Creates an instance
         *
         * @return instance
         */
        @JvmStatic
        fun create(): JsonAndXmlConverters {
            return create(Gson())
        }

        fun create(gson: Gson?): JsonAndXmlConverters {
            if (gson == null) throw NullPointerException("gson == null")
            return JsonAndXmlConverters(gson)
        }
    }
}

//    @Default(value = DefaultType.FIELD)
//    internal class User {
//        @Attribute
//        var name: String? = null
//    }

//    internal interface Service {
//        @GET("/")
//        @Json
//        fun exampleJson(): Call<User>
//
//        @GET("/")
//        @Convert
//        fun exampleXml(): Call<User>
//    }

//    @Throws(IOException::class)
//    @JvmStatic
//    fun main(args: Array<String>) {
//        val server = MockWebServer()
//        server.start()
//        server.enqueue(MockResponse().setBody("{\"name\": \"Jason\"}"))
//        server.enqueue(MockResponse().setBody("<user name=\"Eximel\"/>"))
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(server.url("/"))
//            .addConverterFactory(
//                QualifiedTypeConverterFactory(
//                    GsonConverterFactory.create(),
//                    SimpleXmlConverterFactory.create()
//                )
//            )
//            .build()
//        val service = retrofit.create(Service::class.java)
//
//        val user1 = service.exampleJson().execute().body()
//        println("User 1: " + user1!!.name!!)
//
//        val user2 = service.exampleXml().execute().body()
//        println("User 2: " + user2!!.name!!)
//
//        server.shutdown()
//    }
