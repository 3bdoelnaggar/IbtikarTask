package com.elnaggar.data.repositories

import com.elnaggar.ErrorResult
import com.elnaggar.Result
import com.elnaggar.Success
import com.elnaggar.data.TmdbService
import com.elnaggar.data.entities.Person

class TmdbDataSource(val service: TmdbService) {
    suspend fun getPopularPerson(offset: Int): Result<List<Person>> {
        try {
            val result = service.getPopularPersons()
            return Success(result.results)
        } catch (e: Exception) {
            return ErrorResult(e)
        }
    }
}