package com.example.myapplication.network

import android.content.Context
import com.example.myapplication.utils.isInternetConnectionAvailable
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(private  val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
if (!isInternetConnectionAvailable(context)){
    throw NoConnectionException()
}
        val builder : Request.Builder = chain.request().newBuilder()
        return  chain.proceed(builder.build())
    }
}

class NoConnectionException: IOException(){
    override fun getLocalizedMessage(): String? {
        return "No Internet Connection"
    }
}