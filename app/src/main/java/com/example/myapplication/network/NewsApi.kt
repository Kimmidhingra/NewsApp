package com.example.myapplication.network

import com.example.myapplication.model.News
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(@Query("q") subject:String, @Query("from") from:String) : News

}