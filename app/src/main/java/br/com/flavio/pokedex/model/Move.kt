package br.com.flavio.pokedex.model

import com.google.gson.annotations.SerializedName

data class Move (
    @SerializedName("move") val move: String
)