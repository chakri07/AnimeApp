package edu.uchicago.chakradhar.retroapplication.screens.search.paging

import androidx.paging.PagingData
import edu.uchicago.chakradhar.retroapplication.models.Quote

import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchOperation: SearchOperation = SearchOperation.INITIAL,
    val data: Flow<PagingData<Quote>>? = null
)

enum class SearchOperation { LOADING, INITIAL, DONE }