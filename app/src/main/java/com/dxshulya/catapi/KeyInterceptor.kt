package com.dxshulya.catapi

import okhttp3.Interceptor
import okhttp3.Response

class KeyInterceptor(private val key: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .header("Authorization", "f6880836-42d2-4988-b97b-e87481d59352")
            .method(originalRequest.method(), originalRequest.body())
            .build()
        return chain.proceed(modifiedRequest)
    }

}