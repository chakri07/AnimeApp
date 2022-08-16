package edu.uchicago.chakradhar.retroapplication.youtube

import edu.uchicago.chakradhar.retroapplication.youtube.models.YoutubeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface YoutubeApi {
    @GET(value = "search")
    suspend fun getTrailerLink(
        @Query("part") part: String,
        @Query("maxResults") maxResults: Int,
        @Query("q") keyword: String,
        @Query("key") key: String,
    ): Response<YoutubeResponse>
}