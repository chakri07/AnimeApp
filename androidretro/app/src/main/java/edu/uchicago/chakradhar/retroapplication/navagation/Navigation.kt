package edu.uchicago.chakradhar.retroapplication.navagation

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.uchicago.chakradhar.retroapplication.screens.contact.ContactScreen
import edu.uchicago.chakradhar.retroapplication.screens.contact.ViewModel.ContactViewModel
import edu.uchicago.chakradhar.retroapplication.screens.details.DetailsScreen
import edu.uchicago.chakradhar.retroapplication.screens.favs.FavoritesScreen
import edu.uchicago.chakradhar.retroapplication.screens.favs.favUpdate.UpdateScreen
import edu.uchicago.chakradhar.retroapplication.screens.search.SearchScreen
import edu.uchicago.chakradhar.retroapplication.viewmodels.FavsViewModel
import edu.uchicago.chakradhar.retroapplication.viewmodels.QuoteViewModel
import edu.uchicago.chakradhar.retroapplication.youtube.YoutubeViewModel


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    navController: NavHostController,
) {
    val activity = (LocalContext.current as? Activity)

    val quoteViewModel: QuoteViewModel = hiltViewModel()
    val favsViewModel: FavsViewModel = hiltViewModel()
    val contactViewModel: ContactViewModel = hiltViewModel()
    val youtubeViewModel: YoutubeViewModel = hiltViewModel()
    NavHost(navController, startDestination = Screen.Search.route) {
        composable(Screen.Search.route) {
            SearchScreen(quoteViewModel = quoteViewModel,
                navController = navController,
                favsViewModel)
            BackHandler(enabled = true) {}
        }
        composable(Screen.Detail.route) { backStackEntry ->
            DetailsScreen(quoteViewModel = quoteViewModel,
                navController = navController,
                favsViewModel = favsViewModel,
            youtubeViewModel = youtubeViewModel)
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(navController,
                favsViewModel = favsViewModel)
        }

        composable(Screen.Contact.route) {
            ContactScreen(navController,
                favsViewModel = favsViewModel,
                contactViewModel = contactViewModel)
        }

        composable(Screen.Update.route) {
            UpdateScreen(navController,
                favsViewModel = favsViewModel,
                quoteViewModel = quoteViewModel)
        }
    }
}

