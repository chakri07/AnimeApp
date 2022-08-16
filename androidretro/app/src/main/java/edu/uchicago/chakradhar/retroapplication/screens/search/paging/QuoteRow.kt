package edu.uchicago.chakradhar.retroapplication.screens.search.paging


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import edu.uchicago.chakradhar.retroapplication.models.Quote
import edu.uchicago.chakradhar.retroapplication.viewmodels.FavsViewModel


@ExperimentalAnimationApi
@Composable
fun QuoteRow(
    dbId:String = "",
    quote: Quote,
    onItemClick: (String) -> Unit,
) {
    val dbid :String = dbId
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                quote.anime?.let {
                    //passed down from composable (QuoteList) that instantiates QuoteRow
                    onItemClick(it)
                }
            }
            .padding(10.dp, 5.dp, 5.dp, 10.dp),
        shape = RoundedCornerShape(corner = CornerSize(4.dp)),
        elevation = 12.dp,
        backgroundColor = MaterialTheme.colors.surface

    ) {
        Row(horizontalArrangement = Arrangement.Start) {

            Surface(modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)) {
                //we use coil library here to get fadeIn effect
                val image = rememberCoilPainter(
//                    request = "https://picsum.photos/id/1026/60/90",
                    request = "https://pm1.narvii.com/6361/258bd9f268285fc849d47b8f47c00bb224896add_hq.jpg",
                    fadeIn = true
                )
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .width(60.dp)
                        .height(90.dp),
                    contentScale = ContentScale.FillHeight
                )

            }

            Column() {

                Text(
                    //sometimes, the authors are null; for example when it is a United Nations report
                    text = quote.character ?: "None",
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp
                )
                Text(text = quote.quote ?: "None")
            }
        }
    }
}

