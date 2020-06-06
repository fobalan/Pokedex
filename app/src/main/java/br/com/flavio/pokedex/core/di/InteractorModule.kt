package br.com.flavio.pokedex.core.di

import br.com.flavio.pokedex.feature.pokedexdetail.interactor.GetPokemonDetailInteractor
import br.com.flavio.pokedex.feature.pokedexlist.interactor.GetPokemonListInteractor
import org.koin.dsl.module

val interactorModule = module {
    single { GetPokemonListInteractor(get()) }
    single { GetPokemonDetailInteractor(get()) }
}