package com.example.myapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.myapplication.model.Article
import com.example.myapplication.model.News
import com.example.myapplication.utils.DatabaseConstants.TABLE_NAME_NEWS
import kotlinx.coroutines.flow.Flow

//@Dao
// interface NewsDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun saveAllNews(news: List<Article>)
//
//    @androidx.room.Query("Select * from $TABLE_NAME_NEWS")
//    fun getNews() : Flow<List<Article>>
//}
