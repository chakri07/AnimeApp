package edu.uchicago.chakradhar.retroapplication.viewmodels

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.uchicago.chakradhar.retroapplication.common.Constants
import edu.uchicago.chakradhar.retroapplication.models.Quote
import edu.uchicago.chakradhar.retroapplication.repository.QuotesRepository
import edu.uchicago.chakradhar.retroapplication.screens.search.paging.Paginate
import edu.uchicago.chakradhar.retroapplication.screens.search.paging.QuoteSource
import edu.uchicago.chakradhar.retroapplication.screens.search.paging.SearchOperation
import edu.uchicago.chakradhar.retroapplication.screens.search.paging.SearchState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val quoteRepository: QuotesRepository,
    private val application: Application
) :
    ViewModel() {

    //////////////////////////////////////////
    // MUTABLE-STATES AND OBSERVABLE STATES
    //////////////////////////////////////////
    private var _queryText = mutableStateOf<String>("")
    val queryText: State<String> = _queryText


    private var _quote = mutableStateOf<Quote>(Constants.fakeQuote)
    val quote: State<Quote> = _quote

    private val _searchState = mutableStateOf(SearchState())
    val searchState: State<SearchState> = _searchState

    //////////////////////////////////////////
    // FUNCTIONS
    //////////////////////////////////////////
    fun setQuote(quote: Quote) {
        _quote.value = quote
    }

    fun setQueryText(query: String) {
        _queryText.value = query
    }



    fun onSearch() {
        _searchState.value = SearchState(searchOperation = SearchOperation.LOADING)
        viewModelScope.launch {
            _searchState.value = SearchState(
                data = Pager(
                    config = PagingConfig(pageSize = 10, prefetchDistance = 5),
                    pagingSourceFactory = {
                        QuoteSource(
                            quotesRepository = quoteRepository,
                            paginateData = Paginate(
                                query = _queryText.value
                            ),
                            application
                        )
                    }
                ).flow.cachedIn(viewModelScope),
                searchOperation = SearchOperation.DONE
            )
        }
    }


}


