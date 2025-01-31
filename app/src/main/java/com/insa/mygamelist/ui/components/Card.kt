package com.insa.mygamelist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.insa.mygamelist.R
import com.insa.mygamelist.data.Game

import java.util.StringJoiner


@Composable
fun VideoGameCard(
    navController: NavController, modifier: Modifier = Modifier, game: Game
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
            Row(modifier = Modifier.height(50.dp)) {
                // underlined text
                Text(
                    text = game.name,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    textDecoration = TextDecoration.Underline
                )
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