package br.com.flavio.pokedex.feature.pokedexlist.ui.viewmodel.event

sealed class ViewEvent {
    data class SearchPokemon(val pokemonName: String): ViewEvent()
    data class PokemonItemClick(val pokemonName: String): ViewEvent()
    object ShowProgressBar: ViewEvent()
    object HideProgressBar: ViewEvent()
}