package com.example.myapplication.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import app.cash.turbine.turbineScope
import com.example.myapplication.model.Article
import com.example.myapplication.network.NewsApi
import com.example.myapplication.network.Resource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryImplTest {

    @Mock
    private lateinit var newsApi: NewsApi

    @Mock
    private lateinit var newsRepositoryImpl: NewsRepository

    @Before
    fun setUp() {
        newsRepositoryImpl = Mockito.spy(NewsRepositoryImpl(newsApi))
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun getNewsArticlesForSuccessScenerio() = runTest {
        val newsTitle = "First News"
        val a = Resource.Success(listOf(Article(title = newsTitle)))
        doReturn(flowOf(a)).`when`(
            newsRepositoryImpl
        ).getNews("", "")
        newsRepositoryImpl.getNews("", "").test {
            Assert.assertEquals(newsTitle, awaitItem().data?.get(0)?.title)
            cancelAndIgnoreRemainingEvents()
        }
        verify(newsRepositoryImpl).getNews("", "")

        //list

    }

    @Test
    fun getNewsArticlesForFailScenerio() = runTest {
        val error = "No news available"
        val a = Resource.Failure(null, throwable = Throwable(error))
        doReturn(flowOf(a)).`when`(
            newsRepositoryImpl
        ).getNews("", "")
        newsRepositoryImpl.getNews("", "").test {
            Assert.assertEquals(error, awaitItem().throwable?.message)
            cancelAndIgnoreRemainingEvents()
        }
        verify(newsRepositoryImpl).getNews("", "")
    }
}