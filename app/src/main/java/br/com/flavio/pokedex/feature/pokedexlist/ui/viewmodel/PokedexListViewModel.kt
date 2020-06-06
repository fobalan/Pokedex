package br.com.flavio.pokedex.feature.pokedexlist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.flavio.pokedex.core.LiveEvent
import br.com.flavio.pokedex.feature.pokedexlist.ui.viewmodel.event.ViewEvent
import br.com.flavio.pokedex.feature.pokedexlist.ui.viewmodel.state.ViewState
import br.com.flavio.pokedex.feature.pokedexlist.usecase.PokemonListUseCase
import br.com.flavio.pokedex.feature.pokedexlist.usecase.event.DataEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale

class PokedexListViewModel(
    private val pokemonListUseCase: PokemonListUseCase
) : ViewModel() {

    private var offset: Int = 1

    private val state = MediatorLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state
    private val event = LiveEvent<ViewEvent>()
    val viewEvent: LiveData<ViewEvent> = event

    init {
        state.addSource(pokemonListUseCase.dataEvent) {
            when (it) {
                is DataEvent.ErrorEvent -> offset-= 21
                is DataEvent.LoadingEvent -> event.value = ViewEvent.ShowProgressBar
                is DataEvent.DoneEvent -> event.value = ViewEvent.HideProgressBar
                is DataEvent.SuccessfullEvent -> state.value =
                    ViewState.PokemonListState(it.pokemonList)
            }
        }
    }

    fun getPokemonList() {
        viewModelScope.launch { pokemonListUseCase.getPokemonList(offset) }
    }

    fun pokemonClick(pokemonId: String) {
        event.value = ViewEvent.PokemonItemClick(pokemonId)
    }

    fun scrollPokemonList() {
        offset+= 21
        getPokemonList()
    }

    fun searchClickButton(searchText: String) {
        event.value = ViewEvent.SearchPokemon(searchText.toLowerCase(Locale.getDefault()))
    }
}