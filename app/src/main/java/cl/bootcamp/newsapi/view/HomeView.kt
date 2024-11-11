package cl.bootcamp.newsapi.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.newsapi.modal.Article
import cl.bootcamp.newsapi.newsViewModel.NewsViewModel

@Composable
fun HomeView(viewModel: NewsViewModel) {
    val news by viewModel.news.collectAsState()

    LazyColumn {
        items(news) { item ->
            ArticleCard(item)
        }
    }
}


@Composable
fun ArticleCard(article: Article) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = article.title)
            article.author?.let {
                Text(text = "By $it")
            }
            article.description?.let {
                Text(text = it)
            }
        }
    }
}