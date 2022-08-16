package edu.uchicago.chakradhar.retroapplication.screens.contact.ViewModel

import android.app.Activity
import android.app.Application
import android.widget.Toast
import androidx.compose.material.Text
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.uchicago.chakradhar.retroapplication.dbConnection.DBconnection
import edu.uchicago.chakradhar.retroapplication.screens.contact.Models.ContactForm
import edu.uchicago.chakradhar.retroapplication.screens.contact.Repository.ContactSender
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactSender: ContactSender,
    private val application: Application,
) :
    ViewModel() {
    private var _contactName = mutableStateOf<String>("")
    val contactName: State<String> = _contactName

    private var _contactEmail = mutableStateOf<String>("")
    val contactEmail: State<String> = _contactEmail

    private var _contactMessage = mutableStateOf<String>("")
    val contactMessage: State<String> = _contactMessage

    fun setConctactName(name: String) {
        _contactName.value = name
    }

    fun setConctactEmail(name: String) {
        _contactEmail.value = name
    }

    fun setConctactMessage(name: String) {
        _contactMessage.value = name
    }

    fun sendContactRequest() {
        if (_contactName.value == ""
            || _contactEmail.value == ""
            || _contactMessage.value == "")
        {


        }
            viewModelScope.launch {
                contactSender.sendContactForm(
                    name = _contactName.value,
                    email = _contactEmail.value,
                    body = _contactMessage.value
                )
            }
    }

}