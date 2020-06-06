package br.com.flavio.pokedex.feature.pokedexdetail.di

import android.content.Context
import br.com.flavio.pokedex.feature.pokedexdetail.ui.viewmodel.PokedexDetailViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokedexDetailModule = module {
    factory { Glide.with(get<Context>()) }
    viewModel { (pokemonName: String) -> PokedexDetailViewModel(pokemonName, get(), get()) }
}