package com.elnaggar.data.repositories

import com.elnaggar.ErrorResult
import com.elnaggar.Result
import com.elnaggar.Success
import com.elnaggar.data.TmdbService
import com.elnaggar.data.entities.Person

class TmdbDataSource(private val service: TmdbService) {
    suspend fun getPopularPerson(page: Int=1): Result<List<Person>> {
        try {
            val result = service.getPopularPersons(page)
            return Success(result.results)
        } catch (e: Exception) {
            return ErrorResult(e)
        }
    }
}