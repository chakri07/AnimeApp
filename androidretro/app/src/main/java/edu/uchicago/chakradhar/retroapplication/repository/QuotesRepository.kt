package edu.uchicago.chakradhar.retroapplication.repository

import edu.uchicago.chakradhar.retroapplication.models.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

//simple anime Quotes repo
class QuotesRepository @Inject constructor(private val quotesApi: QuotesApi) {

    //this must be called on a background thread b/c it is long-running
    //here, I pass in the parameters I need, which then re-pass to the instantated interface
    suspend fun getQuotes(
        query: String,
        page: Int,
    ): Response<Array<Quote>> {
        return withContext(Dispatchers.IO) {
            quotesApi.getQuotes(
                title = query,
                page = page
            )
        }
    }
}



