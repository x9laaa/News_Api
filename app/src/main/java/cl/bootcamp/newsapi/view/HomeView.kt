package cl.bootcamp.newsapi.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cl.bootcamp.newsapi.modal.Article
import cl.bootcamp.newsapi.navigation.Screen
import cl.bootcamp.newsapi.newsViewModel.NewsViewModel
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewModel: NewsViewModel, navController: NavHostController) {
    val newsList by viewModel.news.collectAsState()
    val loadMoreNews = remember { mutableStateOf(false) }

    Column {
        TopAppBar(
            title = { Text(text = "Noticias", fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF6200EE),
                titleContentColor = Color.White
            )
        )

        LazyColumn {
            items(newsList.take(5)) { article ->
                ArticleCard(article, navController)
            }

            if (loadMoreNews.value) {
                items(newsList.drop(5)) { article ->
                    ArticleCard(article, navController)
                }
            }

            item {
                LaunchedEffect(Unit) {
                    loadMoreNews.value = true
                }
            }
        }
    }
}

@Composable
fun ArticleCard(article: Article, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.DetailNew.route + "/${article.title}")
            }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                AsyncImage(
                    model = article.urlToImage,
                    contentDescription = "Imagen de la noticia",
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .padding(end = 8.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier.weight(2f)
                ) {
                    Text(
                        text = "Título: ${article.title}",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Fuente: ${article.source.name}",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    article.author?.let {
                        Text(
                            text = "Autor: $it",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

