package br.com.flavio.pokedex.feature.pokedexdetail.usecase.event

import br.com.flavio.pokedex.model.Pokemon

sealed class DataEvent {
    data class SuccessfullEvent(val pokemonDetail: Pokemon) : DataEvent()
    object ErrorEvent: DataEvent()
    object LoadingEvent: DataEvent()
    object DoneEvent: DataEvent()
}