package br.com.flavio.pokedex.core.di

import br.com.flavio.pokedex.feature.pokedexdetail.usecase.PokemonDetailUseCase
import br.com.flavio.pokedex.feature.pokedexlist.usecase.PokemonListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { PokemonDetailUseCase(get()) }
    factory { PokemonListUseCase(get()) }
}