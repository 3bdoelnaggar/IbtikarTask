package com.elnaggar.data

import com.elnaggar.data.entities.Person
import retrofit2.http.GET
import retrofit2.http.Query


const val api_key = "e5bacc18a0877161086a694a97bd477b"

interface TmdbService {
    @GET("person/popular?api_key=$api_key")
    suspend fun getPopularPersons(@Query("page") page: Int): PagedApiResponse<Person>
}