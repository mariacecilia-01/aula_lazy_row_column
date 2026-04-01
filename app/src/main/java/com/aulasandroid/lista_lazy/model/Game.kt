package com.aulasandroid.lista_lazy.model

data class Game(
    val id: Long,
    val title: String,
    val studio: String,
    val releaseYear: Int = 0
)