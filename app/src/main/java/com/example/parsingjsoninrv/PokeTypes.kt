package com.example.parsingjsoninrv

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokeTypes (
    @Json(name = "slot")
    val slot: Int,
    @Json(name = "type")
    val type: PokeType
        ) {
}