package edu.uchicago.chakradhar.retroapplication.screens.contact.Repository

import edu.uchicago.chakradhar.retroapplication.screens.contact.Models.ContactForm
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Singleton


@Singleton
interface ContactApi {
    //this will manage generating the query string and using Retrofit to send GET request to api
    @POST(value = "mail")
    suspend fun contact(
        @Body contactForm: ContactForm,
    ) : Response<ContactForm>}