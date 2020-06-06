package br.com.flavio.pokedex.feature.pokedexdetail.ui.viewmodel.state

import android.graphics.drawable.Drawable
import br.com.flavio.pokedex.model.Type
import com.bumptech.glide.RequestBuilder

sealed class ViewState {
    data class PokemonNameState(val pokemonName: String) : ViewState()
    data class PokemonWeightState(val pokemonWeight: String) : ViewState()
    data class PokemonHeightState(val pokemonHeight: String) : ViewState()
    data class PokemonImageState(val imageBuilder: RequestBuilder<Drawable>) : ViewState()
    data class PokemonTypesState(val types: List<Type>) : ViewState()
    data class PokemonAbilitiesState(val pokemonAbilities: List<String>, val listHeight: Int) :
        ViewState()

    data class PokemonGamesState(val pokemonGames: List<String>, val listHeight: Int) : ViewState()
    data class PokemonMovesState(val pokemonMoves: List<String>, val listHeight: Int) : ViewState()
    object PokemonExpandMoves : ViewState()
    object PokemonHideMoves: ViewState()
    object PokemonExpandGames: ViewState()
    object PokemonHideGames: ViewState()
}