package com.insa.mygamelist.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.insa.mygamelist.data.Game

@Composable
fun VideoGameCardList(
    navController: NavController,
    games: List<Game>,
) {
    LazyColumn {
        items(games.size) { idx ->
            val game = games[idx]
            VideoGameCard(navController = navController, game = game)
        }
    }
}