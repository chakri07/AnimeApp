package edu.uchicago.chakradhar.retroapplication.screens.search

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.amazonaws.mobile.client.*
import edu.uchicago.chakradhar.retroapplication.Cache
import edu.uchicago.chakradhar.retroapplication.MainActivity
import edu.uchicago.chakradhar.retroapplication.R
import edu.uchicago.chakradhar.retroapplication.screens.auth.AuthActivity
import edu.uchicago.chakradhar.retroapplication.screens.search.paging.QuoteList
import edu.uchicago.chakradhar.retroapplication.screens.search.paging.SearchOperation
import edu.uchicago.chakradhar.retroapplication.viewmodels.FavsViewModel
import edu.uchicago.chakradhar.retroapplication.viewmodels.QuoteViewModel
import edu.uchicago.chakradhar.retroapplication.widgets.BottomBar
import edu.uchicago.chakradhar.retroapplication.widgets.CustomTextField
import javax.inject.Inject

@Composable
fun SearchScreen (
    quoteViewModel: QuoteViewModel,
    navController: NavController,
    favsViewModel: FavsViewModel,
) {
    val state = quoteViewModel.searchState.value
    val queryText = quoteViewModel.queryText.value
    val activity = (LocalContext.current as? Activity)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 1.dp,

                ) {
                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Anime Quotes",
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.width(200.dp))
                    Icon(painter = painterResource(id = R.drawable.ic_logout),
                        contentDescription = "Logout",
                        modifier = Modifier.align(Alignment.CenterVertically).padding(20.dp)
                            .clickable {
                                Cache.getInstance().userEmail = ""
                                AWSMobileClient.getInstance().signOut()
                                val intent = Intent(activity, AuthActivity::class.java)
                                activity?.startActivity(intent)
                            },

                    )
                }
            }
        },
        bottomBar = { BottomBar(navController = navController, favsViewModel = favsViewModel) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CustomTextField(
                title = "Search term(s)",
                placeHolder = "e.g. Naruto",
                textState = queryText,
                onTextChange = quoteViewModel::setQueryText,
                keyboardType = KeyboardType.Text,
                ImeAction.Search,
                quoteViewModel::onSearch
            )

            Spacer(modifier = Modifier.height(10.dp))
            when (state.searchOperation) {
                SearchOperation.LOADING -> {
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
                SearchOperation.DONE -> {
                    QuoteList(quoteViewModel, navController)
                }
                else -> {
                    Box {}
                }
            }
        }
    }
}