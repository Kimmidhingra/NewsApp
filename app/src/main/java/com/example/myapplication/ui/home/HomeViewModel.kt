package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Article
import com.example.myapplication.model.News
import com.example.myapplication.network.Resource
import com.example.myapplication.repository.NewsRepository
import com.example.myapplication.utils.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {


    private val _newsArticles = MutableStateFlow<Resource<List<Article>>>(Resource.Loading(null))

    val newsArticles: StateFlow<Resource<List<Article>>> = _newsArticles


    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        _newsArticles.value = Resource.Loading(null)
        viewModelScope.launch(dispatcherProvider.main) {
//            newsRepository.getNews("tesla", "").flowOn(dispatcherProvider.io).map {
//                it.articles
//            }.catch {
//                _newsArticles.value = Resource.Failure(throwable = it)
//            }.collect {
//                if (it.isNotEmpty()) {
//                    _newsArticles.value = Resource.Success(it)
//                } else {
//                    _newsArticles.value =
//                        Resource.Failure(throwable = Throwable("No news available"))
//                }
//
//            }
            newsRepository.getNews("tesla","").flowOn(dispatcherProvider.io).collect{resource->
                    _newsArticles.value = resource
            }
        }
    }


}