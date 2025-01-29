package com.insa.mygamelist.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.insa.mygamelist.data.Game

@Composable
fun VideoGameCardList(games: List<Game>) {
    LazyColumn {
        items(games.size) { idx ->
            VideoGameCard(game = games[idx])
        }
    }
}