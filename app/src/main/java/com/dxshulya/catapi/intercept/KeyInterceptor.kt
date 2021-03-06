package com.dxshulya.catapi.intercept

import okhttp3.Interceptor
import okhttp3.Response

class KeyInterceptor(private val key: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .header("Authorization", key)
            .method(original.method(), original.body())
            .build()
        return chain.proceed(requestBuilder)
    }
}