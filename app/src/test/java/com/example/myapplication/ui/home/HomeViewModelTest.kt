package com.example.myapplication.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.myapplication.model.Article
import com.example.myapplication.model.News
import com.example.myapplication.network.NewsApi
import com.example.myapplication.network.Resource
import com.example.myapplication.repository.NewsRepository
import com.example.myapplication.repository.NewsRepositoryImpl
import com.example.myapplication.utils.DispatcherProvider
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner

//@ExtendWith(InstantExecutorExtension::class)
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    lateinit var newsRepositoryImpl: NewsRepository

    @Mock
    lateinit var api: NewsApi

    @Mock
    private lateinit var dispatcherProvider: DispatcherProvider

    @Mock
    lateinit var viewModel: HomeViewModel


    // in test: we want to run everything synchronously that's why we have to define this rule
    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        newsRepositoryImpl = Mockito.spy(NewsRepositoryImpl(api))
        dispatcherProvider = TestDispatcherProvider()
    }

    @Test
    fun receivedNewsArticlesForSuccess() = runTest {
//        val result =
//            News(articles = listOf(Article(title = "First News")), status = "", totalResults = 10)
//        doReturn(flowOf(result)).`when`(newsRepositoryImpl).getNews("tesla", "")
//        viewModel = HomeViewModel(newsRepositoryImpl, dispatcherProvider)
//
//        viewModel.newsArticles.test {
//            Assert.assertEquals(1, awaitItem().data?.size)
//            cancelAndIgnoreRemainingEvents()
//        }
        val result = Resource.Success(listOf(Article(title = "First News")))
//            Resource<List<(Article(title = "First News"))>>
        doReturn(flowOf(result)).`when`(newsRepositoryImpl).getNews("tesla", "")
        viewModel = HomeViewModel(newsRepositoryImpl, dispatcherProvider)
        viewModel.newsArticles.test {
            Assert.assertEquals(1, awaitItem().data?.size)
            cancelAndIgnoreRemainingEvents()
        }
        Mockito.verify(newsRepositoryImpl).getNews("tesla", "")
    }


    @Test
    fun receivedNewsArticlesForFailure() = runTest {
        val result = Resource.Failure(null, throwable = Throwable("no news available"))
        doReturn(flowOf(result)).`when`(newsRepositoryImpl).getNews("tesla", "")
        viewModel = HomeViewModel(newsRepositoryImpl, dispatcherProvider)
        viewModel.newsArticles.test {

            Assert.assertEquals(true, awaitItem().throwable?.message?.isNotEmpty())
            cancelAndIgnoreRemainingEvents()
        }
        Mockito.verify(newsRepositoryImpl).getNews("tesla", "")
    }
}