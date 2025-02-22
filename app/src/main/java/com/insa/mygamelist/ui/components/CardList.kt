package com.insa.mygamelist.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.insa.mygamelist.data.ViewModels.GamesViewModel

@Composable
fun VideoGameCardList(
    navController: NavController,
    viewModel: GamesViewModel = viewModel()
) {
    val state = viewModel.gamesState

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.games.size) { idx ->
            val game = state.games[idx]
            VideoGameCard(navController = navController, game = game)
            if (idx == state.games.size -1 && state.hasMore) {
                viewModel.fetchGames()
            }
        }

        if (state.isLoading) {
            item {
                CircularProgressIndicator()
            }
        }

        if (state.error != null) {
            item {
                Text(text = state.error)
            }
        }
    }
}