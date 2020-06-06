package br.com.flavio.pokedex.model

import br.com.flavio.pokedex.R
import br.com.flavio.pokedex.core.Constants
import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("type") val type: String
) {
    val drawableId: Int
    get() =
        when (type) {
            Constants.PokemonType.POISON -> R.drawable.type_poison
            Constants.PokemonType.WATER -> R.drawable.type_water
            Constants.PokemonType.ROCK -> R.drawable.type_rock
            Constants.PokemonType.PSYCHIC -> R.drawable.type_psychic
            Constants.PokemonType.NORMAL -> R.drawable.type_normal
            Constants.PokemonType.ICE -> R.drawable.type_ice
            Constants.PokemonType.GROUND -> R.drawable.type_ground
            Constants.PokemonType.GRASS -> R.drawable.type_grass
            Constants.PokemonType.GHOST -> R.drawable.type_ghost
            Constants.PokemonType.FLYING -> R.drawable.type_flying
            Constants.PokemonType.FIRE -> R.drawable.type_fire
            Constants.PokemonType.FIGHTING -> R.drawable.type_fighting
            Constants.PokemonType.FAIRY -> R.drawable.type_fairy
            Constants.PokemonType.ELECTRIC -> R.drawable.type_electric
            Constants.PokemonType.DRAGON -> R.drawable.type_dragon
            Constants.PokemonType.DARK -> R.drawable.type_dark
            Constants.PokemonType.BUG -> R.drawable.type_bug
            else -> R.drawable.type_bug
        }
}