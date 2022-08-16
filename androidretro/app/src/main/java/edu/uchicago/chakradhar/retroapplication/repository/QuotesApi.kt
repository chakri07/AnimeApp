package edu.uchicago.chakradhar.retroapplication.repository

import edu.uchicago.chakradhar.retroapplication.models.Quote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface QuotesApi {

    //this will manage generating the query string and using Retrofit to send GET request to api
    @GET(value = "api/quotes/anime")
    suspend fun getQuotes(
        @Query("title") title: String,
        @Query("page") page: Int,
    ): Response<Array<Quote>>
}