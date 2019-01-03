package com.duongtung.newsapp.base.repository.factory

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import kotlin.annotation.Retention
import java.lang.reflect.Type


class XmlOrJsonConverterFactory : Converter.Factory {
    private final var jsonFactory : Converter.Factory
    private final var xmlFactory : JsonAndXmlConverters

    constructor(jsonFactory:Converter.Factory ,xmlFactory: JsonAndXmlConverters )
    {
        this.jsonFactory = jsonFactory
        this.xmlFactory = xmlFactory
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<kotlin.Annotation>,
        methodAnnotations: Array<kotlin.Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        for ( annotation in parameterAnnotations) {
            if (annotation is Json) {
                return jsonFactory.requestBodyConverter(
                    type, parameterAnnotations, methodAnnotations,
                    retrofit
                )
            }
            if (annotation is Xml) {
                return xmlFactory.requestBodyConverter(
                    type, parameterAnnotations, methodAnnotations,
                    retrofit
                )
            }
        }
        return null
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<kotlin.Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        for (annotation in annotations) {
            if (annotation is Json) {
                return jsonFactory.responseBodyConverter(type, annotations, retrofit)
            }
            if (annotation is Xml) {
                return xmlFactory.responseBodyConverter(type, annotations, retrofit)
            }
        }
        return null
    }

    @Retention(value= AnnotationRetention.RUNTIME)
    annotation class Json

    @Retention(value = AnnotationRetention.RUNTIME)
    annotation class Xml

}

