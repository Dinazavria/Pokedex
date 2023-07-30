package com.example.parsingjsoninrv

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonCard (
    @Json(name = "name")
    val name: String,
    @Json(name = "sprites")
    val sprites: Map<String, String>,
    @Json(name = "types")
    val types: Array<PokeTypes>
        ) {
}