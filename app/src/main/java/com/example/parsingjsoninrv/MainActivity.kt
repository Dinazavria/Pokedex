package com.example.parsingjsoninrv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val request = httpGet("https://pokeapi.co/api/v2/pokemon-form/")
                val moshi = Moshi.Builder()
                    .addLast(KotlinJsonAdapterFactory())
                    .build()
                val jsonAdapter: JsonAdapter<PokemonResponse> = moshi.adapter(
                    PokemonResponse::class.java
                )
            val pokeList = jsonAdapter.fromJson(request) as PokemonResponse
            val pokemons = pokeList.results
            updatePokemons(pokemons)
        }
    }

    fun updatePokemons(pokemons: Array<Pokemon>) {
        val mainScope = CoroutineScope(Dispatchers.Main)
        mainScope.launch{
            val recyclerView: RecyclerView = findViewById(R.id.pokemonList)
            recyclerView.adapter = PokemonListAdapter(pokemons)
        }
    }
}

private suspend fun httpGet(myURL: String?): String {
    val result = withContext(Dispatchers.IO) {
        val inputStream: InputStream

        val url: URL = URL(myURL)

        val conn: HttpsURLConnection = url.openConnection() as HttpsURLConnection

        conn.connect()
        inputStream = conn.inputStream

        inputStream.bufferedReader().use { it.readText() }

    }
    return result
}