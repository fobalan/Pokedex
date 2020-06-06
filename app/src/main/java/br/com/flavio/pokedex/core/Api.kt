package br.com.flavio.pokedex.core

import br.com.flavio.pokedex.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("/{offset}")
    suspend fun getPokemons(@Path("offset") offset: String ) : List<Pokemon>

    @GET("/pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: String) : Pokemon
}