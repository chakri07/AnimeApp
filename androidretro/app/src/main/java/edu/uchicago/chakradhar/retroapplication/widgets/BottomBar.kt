package edu.uchicago.chakradhar.retroapplication.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.uchicago.chakradhar.retroapplication.Cache
import edu.uchicago.chakradhar.retroapplication.R
import edu.uchicago.chakradhar.retroapplication.navagation.Screen
import edu.uchicago.chakradhar.retroapplication.viewmodels.FavsViewModel

@Composable
fun BottomBar(navController: NavController,favsViewModel: FavsViewModel) {
    BottomAppBar() {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            // search favorites contact me
            Icon(painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                modifier = Modifier
                    .clickable {
                        navController.navigate(route = Screen.Search.route)
                    }
                    .align(Alignment.CenterVertically)
                    .padding(20.dp, 0.dp, 20.dp, 0.dp)
            )
            Spacer(modifier = Modifier.width(80.dp))
            Icon(painter = painterResource(id = R.drawable.ic_favorite),
                contentDescription = "Favs",
                modifier = Modifier
                    .align(Alignment.Top)
                    .padding(20.dp, 0.dp, 20.dp, 0.dp)
                    .clickable {
                        favsViewModel.getFavsbyEmail(Cache.getInstance().userEmail)
                        navController.navigate(route = Screen.Favorites.route)
                    }
            )
            Spacer(modifier = Modifier.width(80.dp))
            Icon(painter = painterResource(id = R.drawable.ic_contact),
                contentDescription = "Contact",
                modifier = Modifier
                    .align(Alignment.Top)
                    .padding(20.dp, 0.dp, 20.dp, 0.dp)
                    .clickable {
                        navController.navigate(route = Screen.Contact.route)
                    }
            )


        }
    }
}