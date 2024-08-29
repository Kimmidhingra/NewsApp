package com.example.myapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.NewsRepository
import com.example.myapplication.utils.DispatcherProvider
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(private val repository: NewsRepository, private val dispatcherProvider: DispatcherProvider) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository,dispatcherProvider) as T
    }
}