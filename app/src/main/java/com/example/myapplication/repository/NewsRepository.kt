package com.example.myapplication.repository

import android.util.Log
import com.example.myapplication.model.Article
import com.example.myapplication.model.News
import com.example.myapplication.network.NewsApi
import com.example.myapplication.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface NewsRepository {

    fun getNews(quote: String, time: String): Flow<Resource<List<Article>>>

}

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {
    override fun getNews(quote: String, time: String): Flow<Resource<List<Article>>> = flow {
        Log.i("Kimmi","Thread is: ${Thread.currentThread().name}")
        emit(Resource.Loading(null))
        try {
            val data = api.getNews(quote, time).articles.filter {
                it.title != "[Removed]"
            }
            if (data.isEmpty()) {
                emit(Resource.Failure(throwable = Throwable("News not available")))
            } else {
                emit(Resource.Success(data))
            }
        } catch (throwable: Throwable) {
            emit(Resource.Failure(null, throwable))
        }
//        emit(api.getNews(quote,time))
    }


}