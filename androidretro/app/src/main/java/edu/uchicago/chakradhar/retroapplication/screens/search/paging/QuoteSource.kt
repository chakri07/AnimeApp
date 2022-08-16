package edu.uchicago.chakradhar.retroapplication.screens.search.paging

import android.app.Application
import androidx.paging.PagingSource
import androidx.paging.PagingState
import edu.uchicago.chakradhar.retroapplication.models.Quote
import edu.uchicago.chakradhar.retroapplication.repository.QuotesRepository

import javax.inject.Inject

class QuoteSource @Inject constructor(
    private val quotesRepository: QuotesRepository,
    private val paginateData: Paginate,
    private val application: Application
) :
    PagingSource<Int, Quote>() {
    override fun getRefreshKey(state: PagingState<Int, Quote>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {
        return try {
            val prev = params.key ?: 0

            val response = quotesRepository.getQuotes(
                query = paginateData.query,
                page = paginateData.page
            )

            if (response.isSuccessful) {
                paginateData.page += 1
                val body = response.body()?.asList()
                LoadResult.Page(
                    data = body!!,
                    prevKey = if (prev == 0) null else prev - 1,
                    nextKey = prev + 10
                )
            } else {
                 LoadResult.Error(Exception(response.message()))
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override val keyReuseSupported: Boolean
        get() = true

}