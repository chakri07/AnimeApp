package edu.uchicago.chakradhar.retroapplication.dbConnection

import edu.uchicago.chakradhar.retroapplication.screens.favs.models.FavQuoteResponse
import retrofit2.Response
import retrofit2.http.*
import javax.inject.Singleton
import edu.uchicago.chakradhar.retroapplication.screens.favs.models.FavsQuoteRequest
import retrofit2.Call


@Singleton
interface MongoApiInterface {

    //this will manage generating the query string and using Retrofit to send GET request to api
    @GET(value = "quote")
    suspend fun getAllFavorites(): Response<Array<FavQuoteResponse>>

    @POST("quote")
    suspend fun addFavorite(
        @Body  body:FavsQuoteRequest
    ) : Response<Array<FavQuoteResponse>>

    @GET(value = "quote/{userEmail}")
    suspend fun getFavsbyEmail(
        @Path("userEmail") userEmail: String
    ): Response<Array<FavQuoteResponse>>

    // todo model the delete response
    @DELETE(value = "quote/{id}")
    suspend fun deleteFav(
        @Path("id") id:String,
    ) {

    }

    // todo update the model response
    @PATCH(value = "quote/{id}")
    suspend fun updateFav(
        @Path("id") id:String,
        @Body  body:FavsQuoteRequest
    ) : Response<String>
}