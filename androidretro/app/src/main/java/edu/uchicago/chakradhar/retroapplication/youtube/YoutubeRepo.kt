package edu.uchicago.chakradhar.retroapplication.youtube

import edu.uchicago.chakradhar.retroapplication.common.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class YoutubeRepo @Inject constructor(private val youtubeApi: YoutubeApi){

    suspend fun getTrailerLink(
        query: String,
    ): String{
        var trailerVidoeId:String = ""
         withContext(Dispatchers.IO) {
            val response = youtubeApi.getTrailerLink(
               part = "snippet",
               maxResults = 4,
               key = Constants.utubeAPiKey,
               keyword = query + "Trailer"
           )
             val allResults = response.body()
             trailerVidoeId = allResults?.items?.get(0)?.id?.videoId.toString()
        }
        return trailerVidoeId
    }
}