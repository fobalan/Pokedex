package br.com.flavio.pokedex.feature.pokedexdetail.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.flavio.pokedex.core.LiveEvent
import br.com.flavio.pokedex.extension.dp
import br.com.flavio.pokedex.feature.pokedexdetail.ui.viewmodel.event.ViewEvent
import br.com.flavio.pokedex.feature.pokedexdetail.ui.viewmodel.state.ViewState
import br.com.flavio.pokedex.feature.pokedexdetail.usecase.PokemonDetailUseCase
import br.com.flavio.pokedex.feature.pokedexdetail.usecase.event.DataEvent
import br.com.flavio.pokedex.model.Pokemon
import com.bumptech.glide.RequestManager
import kotlinx.coroutines.launch

class PokedexDetailViewModel(
    pokemonName: String,
    private val pokemonDetailUseCase: PokemonDetailUseCase,
    private val requestManager: RequestManager
) : ViewModel() {
    private val state = MediatorLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state
    private val event = LiveEvent<ViewEvent>()
    val viewEvent: LiveData<ViewEvent> = event

    private val notFoundImage: String =
        "https://cdn.lowgif.com/full/379453c527825283-pokemon-what-gif-find-share-on-giphy.gif"

    private var movesExpanded: Boolean = false
    private var gamesExpanded: Boolean = false
    private var shinyImage: Boolean = false
    private var pokemonId: String = pokemonName
    private lateinit var pokemonDetail: Pokemon

    init {
        state.addSource(pokemonDetailUseCase.dataEvent) {
            when (it) {
                is DataEvent.LoadingEvent -> event.value = ViewEvent.ShowProgressBar
                is DataEvent.DoneEvent -> event.value = ViewEvent.HideProgressBar
                is DataEvent.SuccessfullEvent -> {
                    this.pokemonDetail = it.pokemonDetail
                    configureData(it.pokemonDetail)
                }
                is DataEvent.ErrorEvent -> notFound()
            }
        }
    }

    private fun notFound() {
        event.value = ViewEvent.HideProgressBar
        state.value = ViewState.PokemonImageState(requestManager.load(notFoundImage))
    }

    private fun configureData(pokemonDetail: Pokemon) {
        pokemonId = pokemonDetail.id.toString()
        if (pokemonId == FIRSTPOKEMON) {
            event.value = ViewEvent.HidePreviousButton
        } else {
            event.value = ViewEvent.ShowPreviousButton
        }
        setPokemonImage()
        state.value = ViewState.PokemonNameState("#${pokemonDetail.id} ${pokemonDetail.name}")
        state.value = ViewState.PokemonWeightState("Weight: ${pokemonDetail.weight}hg")
        state.value = ViewState.PokemonHeightState("Height: ${pokemonDetail.height}dm")
        state.value = ViewState.PokemonTypesState(pokemonDetail.types)
        state.value = ViewState.PokemonAbilitiesState(
            pokemonDetail.abilities.map { it.ability },
            pokemonDetail.abilities.size * 36.dp
        )
        state.value = ViewState.PokemonGamesState(
            pokemonDetail.games.map { it.game },
            pokemonDetail.games.size * 36.dp
        )
        state.value = ViewState.PokemonMovesState(
            pokemonDetail.moves.map { it.move },
            pokemonDetail.moves.size * 36.dp
        )
    }

    fun getPokemon() = viewModelScope.launch { pokemonDetailUseCase.getPokemonDetail(pokemonId) }

    fun movesButtonClick() {
        state.value = if (movesExpanded) {
            ViewState.PokemonHideMoves
        } else {
            ViewState.PokemonExpandMoves
        }
        movesExpanded = !movesExpanded
    }

    fun gamesButtonClick() {
        state.value = if (gamesExpanded) {
            ViewState.PokemonHideGames
        } else {
            ViewState.PokemonExpandGames
        }
        gamesExpanded = !gamesExpanded
    }

    fun previousPokemonButtonClick() {
        pokemonId = (pokemonId.toInt() - 1).toString()
        event.value = ViewEvent.PreviousPokemonEvent
    }

    fun nextPokemonButtonClick() {
        pokemonId = (pokemonId.toInt() + 1).toString()
        event.value = ViewEvent.NextPokemonEvent
    }

    fun changePokemonImage() {
        shinyImage = !shinyImage
        setPokemonImage()
    }

    private fun setPokemonImage(){
        if (::pokemonDetail.isInitialized) {
            state.value = if (shinyImage) {
                ViewState.PokemonImageState(requestManager.load(pokemonDetail.imageShiny))
            } else {
                ViewState.PokemonImageState(requestManager.load(pokemonDetail.image))
            }
        }
    }

    companion object {
        private const val FIRSTPOKEMON: String = "1"
    }
}