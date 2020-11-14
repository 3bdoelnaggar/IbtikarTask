package com.elnaggar.data

import com.elnaggar.data.entities.Person
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "e5bacc18a0877161086a694a97bd477b"
const val IMAGE_BASE_URL="https://image.tmdb.org/t/p/w200"

interface TmdbService {
    @GET("person/popular?api_key=$API_KEY")
    suspend fun getPopularPersons(@Query("page") page: Int): PagedApiResponse<Person>
}