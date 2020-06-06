package br.com.flavio.pokedex.model

import com.google.gson.annotations.SerializedName

data class Ability(
    @SerializedName("ability") val ability: String
)
