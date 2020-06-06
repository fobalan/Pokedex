package br.com.flavio.pokedex.feature.pokedexlist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.flavio.pokedex.databinding.ListItemPokemonBinding
import br.com.flavio.pokedex.feature.pokedexlist.ui.viewmodel.PokedexListViewModel
import br.com.flavio.pokedex.model.Pokemon
import com.bumptech.glide.Glide
import java.util.*

class PokemonAdapter(private val viewModel: PokedexListViewModel) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private val pokemons: MutableList<Pokemon> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = pokemons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    fun addAll(pokemons: List<Pokemon>){
        this.pokemons.addAll(pokemons)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ListItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pokemon: Pokemon) {
            binding.pokemonNameTextView.text = pokemon.name.toUpperCase(Locale.ROOT)
            binding.pokemonIdTextView.text = "#${pokemon.id}"
            Glide.with(binding.root).load(pokemon.image).into(binding.pokemonImageView)

            binding.root.setOnClickListener {
                viewModel.pokemonClick(pokemon.name)
            }
        }
    }
}
