package cl.bootcamp.newsapi.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cl.bootcamp.newsapi.newsViewModel.NewsViewModel
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailView(title: String, navController: NavHostController, viewModel: NewsViewModel) {

    val article = viewModel.getArticleByTitle(title).collectAsState(initial = null)
    val uriHandler = LocalUriHandler.current

    Column {
        TopAppBar(
            title = { Text(text = "Noticia") },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver al menú"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF6200EE),
                titleContentColor = Color.White
            )
        )

        article.value?.let {
            Card(
                modifier = Modifier.padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    it.urlToImage?.let { imageUrl ->
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Imagen de la noticia",
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                                .fillMaxWidth()
                        )
                    }
                    Text(text = "Título: ${it.title}", modifier = Modifier.padding(bottom = 8.dp))
                    Text(
                        text = "Fuente: ${it.source.name}",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    it.author?.let { author ->
                        Text(text = "Autor: $author", modifier = Modifier.padding(bottom = 8.dp))
                    }
                    Text(
                        text = "Descripción: ${it.description ?: "Sin descripción"}",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = "URL del artículo: ${it.url}",
                        color = Color.Blue,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .clickable {
                                uriHandler.openUri(it.url)
                            }
                    )
                }
            }
        } ?: run {
            Text(text = "Cargando...", modifier = Modifier.padding(16.dp))
        }
    }
}