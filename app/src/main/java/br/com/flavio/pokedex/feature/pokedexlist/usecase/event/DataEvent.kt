package br.com.flavio.pokedex.feature.pokedexlist.usecase.event

import br.com.flavio.pokedex.model.Pokemon

sealed class DataEvent {
    data class SuccessfullEvent(val pokemonList: List<Pokemon>) : DataEvent()
    object ErrorEvent : DataEvent()
    object LoadingEvent: DataEvent()
    object DoneEvent: DataEvent()
}