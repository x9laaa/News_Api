package cl.bootcamp.newsapi.data

import cl.bootcamp.newsapi.modal.NewsModal
import cl.bootcamp.newsapi.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiNews {
    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsModal>
}