package br.com.flavio.pokedex.core.di

import br.com.flavio.pokedex.core.Api
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL: String = "https://react-pokedex-romaniaph.herokuapp.com"

val baseModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<Api> { get<Retrofit>().create(Api::class.java) }
}