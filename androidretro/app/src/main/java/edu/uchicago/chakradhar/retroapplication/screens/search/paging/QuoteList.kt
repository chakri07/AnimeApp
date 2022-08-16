package edu.uchicago.chakradhar.retroapplication.screens.search.paging


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import edu.uchicago.chakradhar.retroapplication.navagation.Screen
import edu.uchicago.chakradhar.retroapplication.viewmodels.QuoteViewModel

//@OptIn(ExperimentalAnimationApi::class)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun QuoteList(quoteViewModel: QuoteViewModel, navController: NavController) {

    //this is what consumes the flow
    val lazyPagingItems = quoteViewModel.searchState.value.data?.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyPagingItems!!) { quote ->
            QuoteRow(quote = quote!!) {
                //the following lines define the onItemClick behavior
                quoteViewModel.setQuote(quote)
                navController.navigate(
                    route = Screen.Detail.route
                )
            }
        }

        //this will display a spinner in-place of a QuoteRow in the following events
        lazyPagingItems.apply {
            //fallthrough is not supported
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Spinner()
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        Spinner()
                    }
                }
                loadState.prepend is LoadState.Loading -> {
                    item {
                        Spinner()
                    }
                }
            }
        }
    }
}
@Composable
fun Spinner(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(12.dp)
                .align(
                    Alignment.Center
                )
        )
    }
}