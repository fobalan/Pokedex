package br.com.flavio.pokedex.core.di

import br.com.flavio.pokedex.feature.pokedexdetail.di.pokedexDetailModule
import br.com.flavio.pokedex.feature.pokedexlist.di.pokedexListModule

val featureModule = listOf(
    pokedexDetailModule,
    pokedexListModule
)
