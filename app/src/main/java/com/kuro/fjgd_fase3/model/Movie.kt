package com.kuro.fjgd_fase3.model

// modelo de datos que representa una pelicula
data class Movie(
    val id: Int,
    val title: String,
    val genre: String,
    val year: Int,
    val isFavourite: Boolean = false
)