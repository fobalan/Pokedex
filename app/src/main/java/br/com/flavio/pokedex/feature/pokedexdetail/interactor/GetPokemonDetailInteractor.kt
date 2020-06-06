package br.com.flavio.pokedex.feature.pokedexdetail.interactor

import br.com.flavio.pokedex.core.Api
import br.com.flavio.pokedex.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPokemonDetailInteractor(private val api: Api) {
    suspend fun execute(id: String): Pokemon? = withContext(Dispatchers.IO) {
        api.getPokemon(id)
    }
}