package br.com.flavio.pokedex.feature.pokedexlist.usecase

import androidx.lifecycle.LiveData
import br.com.flavio.pokedex.core.LiveEvent
import br.com.flavio.pokedex.feature.pokedexlist.interactor.GetPokemonListInteractor
import br.com.flavio.pokedex.feature.pokedexlist.usecase.event.DataEvent

class PokemonListUseCase(
    private val getPokemonListInteractor: GetPokemonListInteractor
) {
    private val event = LiveEvent<DataEvent>()
    val dataEvent: LiveData<DataEvent> = event

    suspend fun getPokemonList(offset: Int) {
        event.value = DataEvent.LoadingEvent
        try {
            val pokemonList = getPokemonListInteractor.execute(offset)
            event.value = pokemonList?.let {
                DataEvent.SuccessfullEvent(it)
            } ?: DataEvent.ErrorEvent
        } catch (error: Exception) {
            event.value = DataEvent.ErrorEvent
        } finally {
            event.value = DataEvent.DoneEvent
        }
    }
}