package cl.bootcamp.newsapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import cl.bootcamp.newsapi.navigation.NavManager
import cl.bootcamp.newsapi.newsViewModel.NewsViewModel
import cl.bootcamp.newsapi.ui.theme.NewsApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        val viewModel: NewsViewModel by viewModels()
        setContent {
            NewsApiTheme {
                NavManager(viewModel)
            }
        }
    }
}

