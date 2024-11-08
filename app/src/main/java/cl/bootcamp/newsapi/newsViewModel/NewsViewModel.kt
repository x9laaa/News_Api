package cl.bootcamp.newsapi.newsViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.newsapi.modal.Article
import cl.bootcamp.newsapi.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private val _news = MutableStateFlow<List<Article>>(emptyList())
    val news = _news.asStateFlow()

    init {
        fetchNews()
    }


    private fun fetchNews() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getNews("chile")
                if (response.isSuccessful) {
                    val articles = response.body()?.articles
                    _news.value = articles ?: emptyList()
                } else {
                    // Manejo de errores de la API
                    Log.e("NewsViewModel", "Error: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                // Manejo de excepciones
                Log.e("NewsViewModel", "Exception: ${e.message}")
            }
        }
    }
}