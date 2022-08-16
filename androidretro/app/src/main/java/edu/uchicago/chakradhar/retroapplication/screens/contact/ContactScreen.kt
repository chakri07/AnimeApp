package edu.uchicago.chakradhar.retroapplication.screens.contact

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.uchicago.chakradhar.retroapplication.R
import edu.uchicago.chakradhar.retroapplication.screens.contact.ViewModel.ContactViewModel
import edu.uchicago.chakradhar.retroapplication.viewmodels.FavsViewModel
import edu.uchicago.chakradhar.retroapplication.widgets.BottomBar


@Composable
fun ContactScreen(
    navController: NavController,
    favsViewModel: FavsViewModel,
    contactViewModel: ContactViewModel,
) {
    val activity = (LocalContext.current as? Activity)
    Scaffold(
        bottomBar = { BottomBar(navController = navController, favsViewModel = favsViewModel) },
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 1.dp,

                ) {
                Text(
                    text = "Contact Us",
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
                .wrapContentSize(Alignment.Center)
        ) {
            OutlinedTextField(
                value = contactViewModel.contactName.value,
                onValueChange = contactViewModel::setConctactName,
                placeholder = { Text(text = "Please Enter Your Name") }
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = contactViewModel.contactEmail.value,
                onValueChange = contactViewModel::setConctactEmail,
                placeholder = { Text(text = "Please Enter Your Email") }
            )

            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = contactViewModel.contactMessage.value,
                onValueChange = contactViewModel::setConctactMessage,
                placeholder = { Text(text = "Please Enter Your Message(Optional)") },
                modifier = Modifier.height(200.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                if (contactViewModel.contactEmail.value == "" ||
                    contactViewModel.contactName.value == ""
                ) {
                    Toast.makeText(activity, "Please enter all the details", Toast.LENGTH_LONG)
                        .show()
                } else {
                    contactViewModel.sendContactRequest()
                    Toast.makeText(activity, "Request sent", Toast.LENGTH_LONG)
                        .show()
                }
            }
            ) {
                Text(text = "Send Request for contact")
            }

        }
    }
}


@Preview
@Composable
fun ContactScreenPreview() {
//    ContactScreen(rememberNavController(), hiltViewModel())
}