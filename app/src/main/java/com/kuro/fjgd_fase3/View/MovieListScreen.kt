package com.kuro.fjgd_fase3.View

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kuro.fjgd_fase3.ViewModel.MovieViewModel
import com.kuro.fjgd_fase3.model.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    movieViewModel: MovieViewModel = viewModel()
) {
    // aqui obtenemos la lista de peliculas actualizada
    val movieList by movieViewModel.movies.collectAsState()

    // todo este bloque con tanto parentesis y llaves no se bien como explicarlo, solo se que si quito algo la pantalla no funciona o se rompe la interfaz.
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Películas") },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "menu de navegacion"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = movieList,
                key = { movie -> movie.id }
            ) { movie ->
                MovieCard(
                    movie = movie,
                    onToggleFavorite = { movieViewModel.toggleFavorite(movie.id) }
                )
            }
        }
    }
}

@Composable
fun MovieCard(
    movie: Movie,
    onToggleFavorite: () -> Unit
) {
    // toda esta parte de la tarjeta tiene demasiado parentesis y configuracion, no sabria explicarlo todo pero asi se dibuja cada tarjeta en la lista.
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${movie.genre} • ${movie.year}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // boton para marcar o desmarcar favorito
            IconButton(onClick = onToggleFavorite) {
                Icon(
                    imageVector = if (movie.isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (movie.isFavourite) "Quitar de favoritos" else "Añadir a favoritos",
                    tint = if (movie.isFavourite) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}