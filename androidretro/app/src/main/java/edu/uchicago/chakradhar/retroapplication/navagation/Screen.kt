package edu.uchicago.chakradhar.retroapplication.navagation


sealed class Screen(var route: String) {
    object Search : Screen("search")
    object Detail : Screen("detail")
    object Favorites : Screen("favorites")
    object Contact : Screen("contact")
    object Update : Screen("update")
}