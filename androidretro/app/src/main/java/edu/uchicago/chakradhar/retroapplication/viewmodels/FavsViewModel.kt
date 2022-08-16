package edu.uchicago.chakradhar.retroapplication.viewmodels

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.uchicago.chakradhar.retroapplication.common.Constants
import edu.uchicago.chakradhar.retroapplication.dbConnection.DBconnection
import edu.uchicago.chakradhar.retroapplication.screens.favs.models.FavQuoteResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavsViewModel @Inject constructor(
    private val dBconnection: DBconnection,
    private val application: Application,
) :
    ViewModel() {

    private val _favsState = mutableStateListOf<FavQuoteResponse>()
    val favsState: SnapshotStateList<FavQuoteResponse> = _favsState

    private val _selectedFavID = mutableStateOf<String>("")
    val selectedFavID: State<String> = _selectedFavID

    fun setCurrFavId(id: String) {
        _selectedFavID.value = id
    }


    fun addFavs(quote: String, anime: String, character: String, userEmail: String) {
        viewModelScope.launch {
            dBconnection.addFavs(
                quote = quote,
                anime = anime,
                character = character,
                userEmail = userEmail
            )
        }

    }

    fun getFavsbyEmail(userEmail: String) {
        viewModelScope.launch {
            dBconnection.getFavbyEmail(userEmail)
                .body()
                ?.asList()
                ?.let {
                    favsState.clear()
                    favsState.addAll(it)
                }
        }
    }

    fun deleteFav(currentId:String)  {
        viewModelScope.launch {
            dBconnection.deleteFav(currentId)
        }
    }




}