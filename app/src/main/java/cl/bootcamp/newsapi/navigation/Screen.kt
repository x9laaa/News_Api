package cl.bootcamp.newsapi.navigation

sealed class Screen(val route: String) {
    object HomeNews: Screen("home_news")
    object DetailNew: Screen("detail_new")
}