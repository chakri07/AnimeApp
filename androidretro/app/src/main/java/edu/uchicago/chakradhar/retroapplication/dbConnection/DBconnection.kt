package edu.uchicago.chakradhar.retroapplication.dbConnection

import edu.uchicago.chakradhar.retroapplication.screens.favs.models.FavQuoteResponse
import edu.uchicago.chakradhar.retroapplication.screens.favs.models.FavsQuoteRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class DBconnection  @Inject constructor(private val mongoApi: MongoApiInterface) {
    suspend fun getAll(
    ): Response<Array<FavQuoteResponse>> {
        return withContext(Dispatchers.IO) {
            mongoApi.getAllFavorites()
        }
    }

    suspend fun getFavbyEmail(
        userEmail: String,
    ): Response<Array<FavQuoteResponse>> {
        return withContext(Dispatchers.IO) {
            mongoApi.getFavsbyEmail(userEmail)
        }
    }

    suspend fun addFavs(
        quote: String,
        anime: String,
        character: String,
        userEmail: String,
    ): Response<Array<FavQuoteResponse>> {
        return withContext(Dispatchers.IO) {
            val request: FavsQuoteRequest = FavsQuoteRequest(quote, anime, character, userEmail)
            mongoApi.addFavorite(request)
        }
    }

    suspend fun deleteFav(id: String) {
         withContext(Dispatchers.IO) {
            mongoApi.deleteFav(id)
        }

    }

    suspend fun updateFav(
        id: String,
        quote: String,
        anime: String,
        character: String,
        userEmail: String,
    ): Response<String> {
        return withContext(Dispatchers.IO) {
            val request: FavsQuoteRequest = FavsQuoteRequest(quote, anime, character, userEmail)

            mongoApi.updateFav(id,request)
        }

    }
}