package com.insa.mygamelist.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.insa.mygamelist.data.ViewModels.GamesViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: GamesViewModel
) {
    VideoGameCardList(navController = navController, viewModel = viewModel)
}