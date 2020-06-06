package br.com.flavio.pokedex.feature.pokedexlist.ui.viewmodel.state

import br.com.flavio.pokedex.model.Pokemon

sealed class ViewState {
    data class PokemonListState(val pokemonList: List<Pokemon>) : ViewState()
}