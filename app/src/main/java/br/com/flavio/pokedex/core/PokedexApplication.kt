package br.com.flavio.pokedex.core

import android.app.Application
import br.com.flavio.pokedex.core.di.baseModule
import br.com.flavio.pokedex.core.di.featureModule
import br.com.flavio.pokedex.core.di.interactorModule
import br.com.flavio.pokedex.core.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokedexApplication : Application() {

    init {
        startKoin {
            androidLogger()
            androidContext(this@PokedexApplication)
            modules(baseModule + interactorModule + useCaseModule + featureModule)
        }
    }
}