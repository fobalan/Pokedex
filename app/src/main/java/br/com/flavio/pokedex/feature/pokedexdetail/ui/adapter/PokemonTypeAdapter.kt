package br.com.flavio.pokedex.feature.pokedexdetail.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.flavio.pokedex.databinding.ListItemTypeBinding
import br.com.flavio.pokedex.model.Type

class PokemonTypeAdapter(private val types: List<Type>) :
    RecyclerView.Adapter<PokemonTypeAdapter.PokemonTypeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonTypeAdapter.PokemonTypeViewHolder {
        val binding =
            ListItemTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonTypeViewHolder(binding)
    }

    override fun getItemCount(): Int = types.size

    override fun onBindViewHolder(holder: PokemonTypeAdapter.PokemonTypeViewHolder, position: Int) {
        holder.bind(types[position])
    }

    inner class PokemonTypeViewHolder(private val binding: ListItemTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(type: Type) {
            binding.pokemonTypeTextView.text = type.type
            binding.pokemonTypeTextView.setBackgroundResource(type.drawableId)
        }
    }
}
