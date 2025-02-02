package com.insa.mygamelist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.insa.mygamelist.R
import com.insa.mygamelist.data.FavoritesViewModel
import com.insa.mygamelist.data.Game

import java.util.StringJoiner


@Composable
fun VideoGameCard(
    navController: NavController, game: Game,
    viewModel: FavoritesViewModel = viewModel()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                navController.navigate(GameDetailView(id = game.id))
            }, horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Column(modifier = Modifier.weight(0.25f)) {
            val url = "https:" + game.cover.url
            AsyncImage(
                model = url,
                contentDescription = game.name,
                error = painterResource(id = R.drawable.cover_placeholder)
            )
        }
        Column(modifier = Modifier.weight(0.75f)) {

            Row(modifier = Modifier.height(50.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically ) {
                // underlined text
                Text(
                    text = game.name,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.weight(1f)
                )
                val isFavorite by viewModel.favorites.collectAsState()

                // Load favorite state when screen appears
                LaunchedEffect(game.id) {
                    viewModel.loadFavorite(game.id)
                }
                IconToggleButton(
                    checked = isFavorite[game.id] ?: false,
                    onCheckedChange = { viewModel.toggleFavorite(game.id) }
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isFavorite[game.id] == true) R.drawable.ic_star_filled else R.drawable.ic_star_outline
                        ),
                        contentDescription = "Favorite",
                        tint = Color.Black
                    )
                }
            }
            Row(modifier = Modifier.height(50.dp)) {
                val joiner = StringJoiner(", ")

                for (genre in game.genres) {
                    joiner.add(genre.name)
                }
                Text(text = "Genres : $joiner ...", maxLines = 1)
            }
        }
    }
}