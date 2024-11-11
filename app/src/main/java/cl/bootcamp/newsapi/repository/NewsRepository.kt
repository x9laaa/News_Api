package cl.bootcamp.newsapi.repository

import cl.bootcamp.newsapi.modal.NewsModal
import cl.bootcamp.newsapi.data.ApiNews
import cl.bootcamp.newsapi.modal.Article
import cl.bootcamp.newsapi.util.Constants.Companion.API_KEY
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiNews: ApiNews) {
    suspend fun getNews(query: String): Response<NewsModal> {
        return apiNews.getNews(query)
    }

    suspend fun getNewsById(name: String, query: String): Article? {
        val response = apiNews.getNews(query)
        if (response.isSuccessful) {
            return response.body()?.articles?.find { it.source.name == name }
        }
        return null
    }

}