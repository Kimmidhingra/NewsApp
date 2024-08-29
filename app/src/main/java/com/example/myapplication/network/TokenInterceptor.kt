package com.example.myapplication.network

import com.example.myapplication.utils.NetworkConstants
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url =
            request.url.newBuilder().addQueryParameter("apiKey", NetworkConstants.API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}