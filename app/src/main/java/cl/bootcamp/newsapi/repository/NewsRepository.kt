package cl.bootcamp.newsapi.repository

import cl.bootcamp.newsapi.modal.NewsModal
import cl.bootcamp.newsapi.data.ApiNews
import cl.bootcamp.newsapi.util.Constants.Companion.API_KEY
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiNews: ApiNews) {
    suspend fun getNews(frase: String): Response<NewsModal> {
        return apiNews.getNews(frase, API_KEY)

    }

}