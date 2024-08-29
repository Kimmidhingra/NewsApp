package com.example.myapplication.di


import android.app.Application
import com.example.myapplication.network.NetworkConnectionInterceptor
import com.example.myapplication.network.NewsApi
import com.example.myapplication.network.TokenInterceptor
import com.example.myapplication.utils.NetworkConstants.BASE_URL
import com.example.myapplication.utils.NetworkConstants.CONNECTION_TIME_OUT_IN_SECS
import com.example.myapplication.utils.NetworkConstants.READ_TIME_OUT_IN_SECS
import com.example.myapplication.utils.NetworkConstants.WRITE_TIME_OUT_IN_SECS
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NewsModule {

    @Provides
    @Singleton
    fun buildNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build()

    @Provides
    @Singleton
    fun provideOkHttp(application: Application) = OkHttpClient.Builder().apply {
        readTimeout(READ_TIME_OUT_IN_SECS, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIME_OUT_IN_SECS, TimeUnit.SECONDS)
        connectTimeout(CONNECTION_TIME_OUT_IN_SECS, TimeUnit.SECONDS)
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }.let {
            addInterceptor(it)
        }
        addInterceptor(TokenInterceptor())
        addInterceptor(NetworkConnectionInterceptor(application.applicationContext))
    }.build()

//    @Provides
//    @Singleton
//    fun providesDatabase(application: Application): NewsDatabase {
//        return Room.databaseBuilder(
//            application.applicationContext,
//            NewsDatabase::class.java,
//            DatabaseConstants.DATABASE_NAME
//        ).build()
//    }

}