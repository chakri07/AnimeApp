package edu.uchicago.chakradhar.retroapplication.screens.favs

import android.app.Activity
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import edu.uchicago.chakradhar.retroapplication.R
import edu.uchicago.chakradhar.retroapplication.models.Quote
import edu.uchicago.chakradhar.retroapplication.navagation.Screen
import edu.uchicago.chakradhar.retroapplication.screens.search.paging.QuoteRow
import edu.uchicago.chakradhar.retroapplication.viewmodels.FavsViewModel
import edu.uchicago.chakradhar.retroapplication.widgets.BottomBar


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FavoritesScreen(navController: NavController,favsViewModel: FavsViewModel) {
//    val lazyPagingItems = quoteViewModel.searchState.value.data?.collectAsLazyPagingItems()
    val favsList = favsViewModel.favsState
    val activity = (LocalContext.current as? Activity)
    Scaffold(
        bottomBar  = { BottomBar(navController = navController,favsViewModel) },
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 1.dp,

                ) {
                Text(
                    text = "Favorites",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.cardview_shadow_start_color))
                .wrapContentSize(Alignment.Center)
        ) {
            favsList.forEach { currentFav ->
                val favQuote = Quote()
                favQuote.anime = currentFav.anime
                favQuote.character = currentFav.character
                favQuote.quote = currentFav.quote
                QuoteRow(quote = favQuote, dbId = currentFav.id){
                    favsViewModel.setCurrFavId(currentFav.id)
                    navController.navigate(Screen.Update.route)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    //FavoritesScreen()
}