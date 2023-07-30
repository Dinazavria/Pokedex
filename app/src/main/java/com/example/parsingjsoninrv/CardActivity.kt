package com.example.parsingjsoninrv

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.lang.StringBuilder
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class CardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        val url = intent.getStringExtra(RESOURCE)


        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val request = httpGet(url)
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val jsonAdapter: JsonAdapter<PokemonCard> = moshi.adapter(
                PokemonCard::class.java
            )
            val pokemonCard = jsonAdapter.fromJson(request) as PokemonCard
            renderCard(pokemonCard)
        }

    }

    fun renderCard(pokemon: PokemonCard) {
        val mainScope = CoroutineScope(Dispatchers.Main)
        mainScope.launch{
            val name: TextView = findViewById(R.id.namePokemon)
            name.text = pokemon.name.replaceFirstChar(Char::titlecase)
            val img: ImageView = findViewById(R.id.frontImg)
            Glide
                .with(img.context)
                .load(pokemon.sprites.get("front_default"))
                .into(img)
            val types: TextView = findViewById(R.id.types)
            val typeString = StringBuilder()
            for(item in pokemon.types) {
                typeString.append(item.type.type)
                    .append(" ")
            }
            types.text = typeString.toString()
        }
    }

    companion object {
        const val RESOURCE = "url"
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