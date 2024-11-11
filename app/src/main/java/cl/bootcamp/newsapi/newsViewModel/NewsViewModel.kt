package cl.bootcamp.newsapi.newsViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.newsapi.modal.Article
import cl.bootcamp.newsapi.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private val _news = MutableStateFlow<List<Article>>(emptyList())
    val news = _news.asStateFlow()

    private var query = "chile"

    init {
        fetchAllNews(query)
    }

    private fun fetchAllNews(query: String) {
        viewModelScope.launch {
            val response = repository.getNews(query)
            if (response.isSuccessful) {
                _news.value = response.body()?.articles ?: emptyList()
            } else {
                Log.e("NewsViewModel", "Error: ${response.errorBody()}")
            }
        }
    }

}