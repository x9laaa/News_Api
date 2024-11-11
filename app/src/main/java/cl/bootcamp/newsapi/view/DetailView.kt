package cl.bootcamp.newsapi.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cl.bootcamp.newsapi.newsViewModel.NewsViewModel
import coil.compose.AsyncImage

@Composable
fun DetailView(title: String, navController: NavHostController, viewModel: NewsViewModel) {
    val article = viewModel.getArticleByTitle(title)

    article.let {
        Card(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                AsyncImage(
                    model = article.value.urlToImage,
                    contentDescription = "Imagen de la noticia",
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(text = "Título: ${it.value.title}", modifier = Modifier.padding(bottom = 8.dp))
                Text(text = "Fuente: ${it.source.name}", modifier = Modifier.padding(bottom = 8.dp))
                it.author?.let { author ->
                    Text(text = "Autor: $author", modifier = Modifier.padding(bottom = 8.dp))
                }
                Text(text = "Descripción: ${it.description ?: "Sin descripción"}")
            }
        }
    }
}