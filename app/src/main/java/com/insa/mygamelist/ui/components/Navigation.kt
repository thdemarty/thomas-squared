package com.insa.mygamelist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.insa.mygamelist.data.IGDB
import kotlinx.serialization.Serializable
import kotlin.system.exitProcess

@Serializable
object HomeView

@Serializable
data class GameDetailView(val id: Long)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost() {
    val controller = rememberNavController()
    val navBackStackEntry = controller.currentBackStackEntryAsState().value
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        topBar = {
            if (currentDestination == null || currentDestination.hasRoute<HomeView>()) {
                // On Home View
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            // Quit the application
                            exitProcess(0)

                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = topAppBarColors(
                        containerColor = Color.Magenta,
                        titleContentColor = Color.Black,
                    ),
                    title = { Text("MyGamesList") },
                    )
            } else {
                // On Game Detail View
                val backStackEntry = controller.currentBackStackEntry
                if (backStackEntry != null) {
                    val gameId = backStackEntry.toRoute<GameDetailView>().id
                    val game = IGDB.games.find { it.id == gameId }

                    if (game == null) {
                        TopAppBar(title = { Text("Game Detail View - Error") })
                    } else {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = Color.Magenta,
                                titleContentColor = Color.Black,
                            ),
                            title = { Text(game.name) },
                            navigationIcon = {
                                IconButton(onClick = {
                                    controller.popBackStack()
                                }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        )
                    }


                }
            }
        }, modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = controller, startDestination = HomeView) {
                composable<HomeView> {
                    HomeScreen(navController = controller)
                }
                composable<GameDetailView> { backStackEntry ->
                    val view: GameDetailView = backStackEntry.toRoute()
                    GameDetailScreen(navController = controller, gameId = view.id)
                }
            }
        }
    }
}


