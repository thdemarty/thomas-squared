package com.insa.mygamelist.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.insa.mygamelist.data.ViewModels.GamesViewModel
import com.insa.mygamelist.data.models.Game

@Composable
fun VideoGameCardList(
    navController: NavController,
    games: List<Game>,
    viewModel: GamesViewModel = viewModel()
    ) {

    val state = viewModel.gamesState

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(games.size) { idx ->
            val game = games[idx]
            VideoGameCard(navController = navController, game = game)
            val isSearching = state.searchQuery == ""
            if (!isSearching && idx == games.size -1 && state.hasMore && state.searchQuery.isBlank()) {
                viewModel.fetchGames()
            }
        }

        if (state.isLoading) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        if (state.error != null) {
            item {
                Text(text = state.error)
            }
        }
    }
}