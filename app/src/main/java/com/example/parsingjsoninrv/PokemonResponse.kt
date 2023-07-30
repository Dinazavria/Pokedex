package com.example.parsingjsoninrv

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "next")
    val n: String?,
    @Json(name = "results")
    val results: Array<Pokemon>
) {
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as PokemonResponse
//
//        if (count != other.count) return false
//        if (n != other.n) return false
//        if (!results.contentEquals(other.results)) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = count
//        result = 31 * result + n.hashCode()
//        result = 31 * result + results.contentHashCode()
//        return result
//    }
}