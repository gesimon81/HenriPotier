package fr.android.paintersimon.domain

import retrofit2.http.GET
import retrofit2.http.Path

interface HenriPotierService {
    @GET("/books")
    suspend fun listBooks(): List<Book>

    @GET("/books/{isbns}/commercialOffers")
    suspend fun getCommercialOffer(@Path("isbns") isbns: String):Offers
}
