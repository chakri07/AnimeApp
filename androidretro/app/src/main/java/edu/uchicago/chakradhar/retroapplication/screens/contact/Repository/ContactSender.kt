package edu.uchicago.chakradhar.retroapplication.screens.contact.Repository

import android.provider.ContactsContract
import edu.uchicago.chakradhar.retroapplication.dbConnection.MongoApiInterface
import edu.uchicago.chakradhar.retroapplication.screens.contact.Models.ContactForm
import javax.inject.Inject

class ContactSender @Inject constructor(private val contactApi: ContactApi) {
    suspend fun sendContactForm(
        name:String,
        body:String,
        email: String
    ) {
        val contactForm = ContactForm(name, body, email)
        contactApi.contact(contactForm)
    }
}