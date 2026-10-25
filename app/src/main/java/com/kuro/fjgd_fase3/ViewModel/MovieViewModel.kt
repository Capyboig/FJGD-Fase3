package com.kuro.fjgd_fase3.ViewModel

import com.kuro.fjgd_fase3.model.Movie
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MovieViewModel : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())

    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    init {
        loadInitialMovies()
    }

    // aqui cargamos las peliculas iniciales en la lista
    private fun loadInitialMovies() {
        _movies.value = listOf(
            Movie(id = 1, title = "Buddy", genre = "Terror / Black Comedy", year = 2026),
            Movie(id = 2, title = "El Padrino", genre = "Crimen / Drama", year = 1972),
            Movie(id = 3, title = "Pulp Fiction", genre = "Crimen", year = 1994),
            Movie(id = 4, title = "El Caballero Oscuro", genre = "Acción", year = 2008),
            Movie(id = 5, title = "Inception", genre = "Ciencia Ficción", year = 2010),
            Movie(id = 6, title = "Matrix", genre = "Ciencia Ficción", year = 1999),
            Movie(id = 7, title = "Gladiator", genre = "Acción / Aventura", year = 2000),
            Movie(id = 8, title = "Parásitos", genre = "Drama / Suspense", year = 2019)
        )
    }

    // funcion para cambiar el estado de favorito de una pelicula por su id
    fun toggleFavorite(movieId: Int) {
        _movies.update { currentList ->
            currentList.map { movie ->
                if (movie.id == movieId) {
                    movie.copy(isFavourite = !movie.isFavourite)
                } else {
                    movie
                }
            }
        }
    }
}