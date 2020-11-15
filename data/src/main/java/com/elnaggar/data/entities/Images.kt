package com.elnaggar.data.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Images(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "profiles")
    val profiles: List<Profile>
)