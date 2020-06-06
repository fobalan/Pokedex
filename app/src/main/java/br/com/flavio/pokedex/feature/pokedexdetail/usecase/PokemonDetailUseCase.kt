package br.com.flavio.pokedex.feature.pokedexdetail.usecase

import androidx.lifecycle.LiveData
import br.com.flavio.pokedex.core.LiveEvent
import br.com.flavio.pokedex.feature.pokedexdetail.interactor.GetPokemonDetailInteractor
import br.com.flavio.pokedex.feature.pokedexdetail.usecase.event.DataEvent
import java.lang.Exception

class PokemonDetailUseCase(private val getPokemonDetailInteractor: GetPokemonDetailInteractor) {

    private val event = LiveEvent<DataEvent>()
    val dataEvent: LiveData<DataEvent> = event

    suspend fun getPokemonDetail(id: String) {
        try {
            event.value = DataEvent.LoadingEvent
            val pokemonDetail = getPokemonDetailInteractor.execute(id)
            event.value = pokemonDetail?.let {
                DataEvent.SuccessfullEvent(it)
            } ?: DataEvent.ErrorEvent
        } catch (error: Exception) {
            event.value = DataEvent.ErrorEvent
        } finally {
            event.value = DataEvent.DoneEvent
        }
    }
}