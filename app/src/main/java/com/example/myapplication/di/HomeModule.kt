package com.example.myapplication.di


import com.example.myapplication.repository.NewsRepository
import com.example.myapplication.repository.NewsRepositoryImpl
import com.example.myapplication.utils.DispatcherProvider
import com.example.myapplication.utils.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@Module
abstract class HomeModule {
    @Binds
    abstract fun bindNewsRepository(fragment: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun bindDispatcher(dispatcher: DispatcherProviderImpl): DispatcherProvider
}