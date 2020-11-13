package com.elnaggar.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PagedApiResponse<out R>(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<R>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)