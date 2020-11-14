package com.elnaggar.data.repositories

import com.elnaggar.ErrorResult
import com.elnaggar.Result
import com.elnaggar.Success
import com.elnaggar.data.TmdbService
import com.elnaggar.data.entities.Person
import com.elnaggar.data.entities.PersonDetails

class TmdbDataSource(private val service: TmdbService) {
    suspend fun getPopularPerson(page: Int=1): Result<List<Person>> {
        return try {
            val result = service.getPopularPersons(page)
            Success(result.results)
        } catch (e: Exception) {
            ErrorResult(e)
        }
    }

    suspend fun getPersonDetails(id: String): Result<PersonDetails> {
        return try {
            val result = service.getPersonDetails(id)
            Success(result)
        } catch (e: Exception) {
            ErrorResult(e)
        }
    }
}