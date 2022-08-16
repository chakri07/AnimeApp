package edu.uchicago.chakradhar.retroapplication.screens.details


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.amazonaws.mobile.auth.userpools.SignUpActivity.startActivity
import com.skydoves.landscapist.glide.GlideImage
import edu.uchicago.chakradhar.retroapplication.Cache
import edu.uchicago.chakradhar.retroapplication.R
import edu.uchicago.chakradhar.retroapplication.viewmodels.FavsViewModel
import edu.uchicago.chakradhar.retroapplication.viewmodels.QuoteViewModel
import edu.uchicago.chakradhar.retroapplication.youtube.YoutubeRepo
import edu.uchicago.chakradhar.retroapplication.youtube.YoutubeViewModel


@Composable
fun DetailsScreen(
    navController: NavController,
    quoteViewModel: QuoteViewModel,
    favsViewModel: FavsViewModel,
    youtubeViewModel: YoutubeViewModel
) {

    //observe the book
    val quote = quoteViewModel.quote.value
    val activity = (LocalContext.current as? Activity)

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 1.dp
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                        .align(Alignment.CenterVertically)
                        .padding(20.dp, 0.dp, 0.dp, 0.dp))

                Text(
                    text = "Details",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {

                    Icon(imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        modifier = Modifier
                            .clickable {
                                val sendIntent = Intent(Intent.ACTION_SEND)
                                sendIntent.type = "text/plain"
                                sendIntent.putExtra(
                                    Intent.EXTRA_TEXT,
                                    "You must check out this Quote!: ${quote.quote}"
                                )
                                activity?.startActivity(sendIntent)
                            }
                            .align(Alignment.CenterVertically)
                            .padding(10.dp, 0.dp, 0.dp, 0.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                }

            }

        }
    }) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState(0))
                .padding(20.dp, 0.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {


                Divider()
                Spacer(Modifier.height(20.dp))
                //we can use either coil or glide for images. If you need animations, use coil
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(300.dp),
                    imageModel = "https://pm1.narvii.com/6361/258bd9f268285fc849d47b8f47c00bb224896add_hq.jpg",
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.Fit

                )

                quote.quote?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Start,
                        fontSize = 22.sp
                    )
                }
                quote.character?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(fontWeight = FontWeight.Normal),
                        textAlign = TextAlign.Start,
                        fontSize = 16.sp
                    )
                }


                Button(
                    modifier =
                    Modifier
                        .padding(10.dp, 0.dp)
                        .fillMaxWidth(1f),

                    onClick = {
                        Toast.makeText(activity, "Added to your Favorites", Toast.LENGTH_LONG).show()
                        favsViewModel.addFavs(
                            quote = quote.quote,
                            anime = quote.anime,
                            character = quote.character,
                            userEmail = Cache.getInstance().userEmail
                        )
                    },

                    colors =
                    ButtonDefaults.buttonColors(backgroundColor = Color.Green)

                ) {
                    Text(text = "Add to Favorites")
                }

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier =
                    Modifier
                        .padding(10.dp, 0.dp)
                        .fillMaxWidth(1f),

                    onClick = {
                        youtubeViewModel.getTrailerLink(quote.anime)
                        Thread.sleep(1500)
                        val trailer:String = youtubeViewModel.trailerVideoId.value
                        var uri = ""
                        val rickroll = "dQw4w9WgXcQ"
                        uri = if (youtubeViewModel.count ==0 ){
                            "http://www.youtube.com/watch?v=$rickroll"
                        }else{
                            "http://www.youtube.com/watch?v=$trailer"
                        }
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(uri)
                        )
                        youtubeViewModel.count+=1
                        activity?.startActivity(intent)
                    },
                    colors =
                    ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text(text = "Watch anime Trailer in youtube")
                }

            }
        }
    }
}




