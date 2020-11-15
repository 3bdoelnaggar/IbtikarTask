package com.elnaggar.data

import com.elnaggar.data.entities.Images
import com.elnaggar.data.entities.Person
import com.elnaggar.data.entities.PersonDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val API_KEY = "e5bacc18a0877161086a694a97bd477b"
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w200"

interface TmdbService {
    @GET("person/popular?api_key=$API_KEY")
    suspend fun getPopularPersons(@Query("page") page: Int): PagedApiResponse<Person>

    //https://api.themoviedb.org/3/person/1397778?api_key=e5bacc18a0877161086a694a97bd477b
    @GET("person/{personId}?api_key=$API_KEY")
    suspend fun getPersonDetails(@Path("personId") id: String): PersonDetails

    //https://api.themoviedb.org/3/person/1397778/images?api_key=e5bacc18a0877161086a694a97bd477b
    @GET("person/{personId}/images?api_key=$API_KEY")
    suspend fun getPersonImages(@Path("personId") id: String): Images
}