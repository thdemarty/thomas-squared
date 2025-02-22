package com.insa.mygamelist.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun HomeScreen(
    navController: NavController,
) {
    VideoGameCardList(navController = navController)
}