package edu.uchicago.chakradhar.retroapplication.youtube

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.uchicago.chakradhar.retroapplication.repository.QuotesRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel

class YoutubeViewModel @Inject constructor(
    private val youtubeRepo: YoutubeRepo,
    ) :
    ViewModel() {

    private var _traildVideoId = mutableStateOf<String>("")
    val trailerVideoId: State<String> = _traildVideoId

    var count = 0


    fun getTrailerLink(keyword: String) {
        runBlocking {
            _traildVideoId.value = youtubeRepo.getTrailerLink(keyword)
        }
        return
    }
}