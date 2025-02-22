package com.insa.mygamelist.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.insa.mygamelist.data.ViewModels.GamesViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: GamesViewModel
) {
    val state = viewModel.gamesState
    VideoGameCardList(navController = navController, games = state.games, viewModel = viewModel)
}