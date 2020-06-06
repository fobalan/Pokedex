package br.com.flavio.pokedex.feature.pokedexlist.di

import br.com.flavio.pokedex.feature.pokedexlist.ui.adapter.PokemonAdapter
import br.com.flavio.pokedex.feature.pokedexlist.ui.viewmodel.PokedexListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokedexListModule = module {
        viewModel { PokedexListViewModel(get()) }
}