package edu.uchicago.chakradhar.retroapplication.common


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.uchicago.chakradhar.retroapplication.models.Quote
import edu.uchicago.chakradhar.retroapplication.screens.favs.models.FavQuoteResponse


object Constants {

    val quoteApiUrl = "https://animechan.vercel.app/"
    val mongoDBUrl = "https://fullstack-final.9s4t2ca60nlac.us-east-1.cs.amazonlightsail.com/"
    val contactUrl = "https://lkdzggekod.execute-api.us-east-1.amazonaws.com/Prod/"
    val youtubeUrl = "https://youtube.googleapis.com/youtube/v3/"

    val utubeAPiKey = "AIzaSyDjDFeKAVRXcpgqmCCjSLqGuzE7ULqD0oo"


    val fakeQuote: Quote
    val fakeResp: Array<FavQuoteResponse>

    private var sampleResponseForByEmail: String = """[
    {
        "id": "62f47cf0e8ffc551aa47ced7",
        "quote": "quote111",
        "anime": "nbaruotttt",
        "character": "jiraya",
        "userEmail": "naruto@gmail.com"
    },
    {
        "id": "62f47cfbe8ffc551aa47ced8",
        "quote": "qutoesekf121",
        "anime": "nbaruotttt",
        "character": "jiraya",
        "userEmail": "naruto@gmail.com"
    }
]"""

    //use init to parse the raw response-body
    init {
        val gson = Gson()
        val hardCodedResponse = """ {
        "anime": "Naruto",
        "character": "Yashamaru",
        "quote": "Physical wounds will definitely bleed and may look painful \nbut over time they heal by themselves and if you apply medicine, \nthey will heal faster. What's troublesome are wounds of the heart. Nothing is harder to heal. They're a bit different from physical injuries. You can't apply medicine for one thing and sometimes, they never heal. There's only one cure for a wound of the heart. \nIt's a bit bothersome and you can only receive it from someone else. What is it? Love."
    }"""

        fakeQuote = gson.fromJson<Quote>(hardCodedResponse, Quote::class.java)
        val userListType = object : TypeToken<Array<FavQuoteResponse?>?>() {}.type

        fakeResp = gson.fromJson(sampleResponseForByEmail, userListType)
        for (quote in fakeResp) {
            System.out.println(quote)
        }

    }
}