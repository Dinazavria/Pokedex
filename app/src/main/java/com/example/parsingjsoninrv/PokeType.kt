package com.example.parsingjsoninrv

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokeType (
    @Json(name = "name")
    val type: String,
    @Json(name = "url")
    val url: String
        ){
}