package com.insa.mygamelist.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.insa.mygamelist.data.IGDBStatic


@Composable
fun HomeScreen(navController: NavController) {
    VideoGameCardList(navController = navController, games = IGDBStatic.games)
}