package com.insa.mygamelist.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.insa.mygamelist.data.Game
import com.insa.mygamelist.data.IGDB

@Composable
fun GameDetailScreen(
    navController: NavController,
    gameId: Long
) {

    if (gameId > -1L) {
        // There is a game with the given id
        val game = IGDB.games.find { it.id == gameId }
        if (game != null) {
            Text(text = game.name)
        }
    } else {
        Text("No game found")
    }

}

@Composable
fun HomeScreen(navController: NavController) {
    VideoGameCardList(navController = navController, games = IGDB.games)
}