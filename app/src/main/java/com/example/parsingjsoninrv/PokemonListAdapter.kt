package com.example.parsingjsoninrv

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PokemonListAdapter(private val pokemons: Array<Pokemon>) :
    RecyclerView.Adapter<PokemonListAdapter.PokeViewHolder>() {

    inner class PokeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item, parent, false)
        return PokeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        holder.name.text = pokemons[position].name.replaceFirstChar(Char::titlecase)

        holder.itemView.setOnClickListener {
            val context = holder.name.context
            val intent = Intent(context, CardActivity::class.java)
            intent.putExtra(CardActivity.RESOURCE, pokemons[position].url)
            context.startActivity(intent)
        }

    }

}

