package com.example.myapplication.repository

import com.example.myapplication.model.Article
import com.example.myapplication.model.News
import com.example.myapplication.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//class FakeNewsRepository : NewsRepository {
//    override fun getNews(quote: String, time: String): Flow<Resource<List<Article>>> = flow {
////        val list = listOf(Article(title = "News 1"))
////        for (i in 1..5) {
////            list.add(Article(title = "News $i"))
////        }
//        val news = News(articles = mutableListOf(Article(title = "first News")), status = "", totalResults = 100)
//        emit(Resource.Loading(null))
//
//        emit(Resource.Success(news.articles))
////        emit(Resource.Failure(null, Throwable("No internet Connection")))
//    }
//}