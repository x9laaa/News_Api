package cl.bootcamp.newsapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.bootcamp.newsapi.newsViewModel.NewsViewModel
import cl.bootcamp.newsapi.view.HomeView


@Composable
fun NavManager(viewModel: NewsViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.HomeNews.route) {
        composable(Screen.HomeNews.route) {
            HomeView(viewModel, navController)
        }

        composable(
            route = Screen.DetailNew.route + "/{title}",
            arguments = listOf(navArgument("title") {
                type = NavType.StringType
                defaultValue = ""
                nullable = false
            })
        ) { entry ->
            val title = entry.arguments?.getString("title") ?: ""
            DetailView(title, navController, viewModel)
        }
    }
}



