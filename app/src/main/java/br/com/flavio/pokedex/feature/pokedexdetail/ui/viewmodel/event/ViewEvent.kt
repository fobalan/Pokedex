package br.com.flavio.pokedex.feature.pokedexdetail.ui.viewmodel.event

sealed class ViewEvent {
    object ShowProgressBar: ViewEvent()
    object HideProgressBar: ViewEvent()
    object NextPokemonEvent: ViewEvent()
    object PreviousPokemonEvent: ViewEvent()
    object HidePreviousButton: ViewEvent()
    object ShowPreviousButton: ViewEvent()
    object HideNextButton: ViewEvent()
    object ShowNextButton: ViewEvent()
}