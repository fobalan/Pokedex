package br.com.flavio.pokedex.feature.pokedexlist.interactor

import br.com.flavio.pokedex.core.Api
import br.com.flavio.pokedex.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPokemonListInteractor (private val api: Api) {
    suspend fun execute(offset: Int): List<Pokemon>? = withContext(Dispatchers.IO){
        api.getPokemons(offset.toString())
    }
}