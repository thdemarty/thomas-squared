package com.insa.mygamelist.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.insa.mygamelist.R
import com.insa.mygamelist.data.IGDB
import java.util.StringJoiner

@Composable
fun GameDetailScreen(
    navController: NavController,
    gameId: Long
) {

    if (gameId > -1L) {
        val game = IGDB.games.find { it.id == gameId }

        if (game != null) {
            LazyColumn (modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            ) {
                item {
                    Row {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = game.name,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row {
                        val url = "https:" + game.cover.url
                        AsyncImage(
                            modifier = Modifier.fillMaxWidth().height(250.dp).padding(top = 20.dp),
                            model = url,
                            alignment = Alignment.Center,
                            contentDescription = game.name
                        )
                    }
                    Row (
                        modifier = Modifier.padding(10.dp).fillMaxWidth()
                    ){
                        val joiner = StringJoiner(", ")

                        for (genre in game.genres) {
                            joiner.add(genre.name)
                        }
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "$joiner",
                            maxLines = 1,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            fontStyle = FontStyle.Italic
                        )
                    }
                    LazyRow {

                        items(game.platforms.size) { idx ->
                            val platform = game.platforms[idx]
                            val url = "https:" + platform.logo?.url
                            AsyncImage(
                                modifier = Modifier.height(80.dp).width(80.dp).padding(horizontal = 6.dp),
                                model = url,
                                contentDescription = platform.name,
                                error = painterResource(id = R.drawable.no_photo)
                            )
                        }
                    }
                    Row {
                        Text(text = game.summary, textAlign = TextAlign.Justify)
                    }

                }

            }
        }
    } else {
        Text(
            text = "No game id for this game",
            textAlign = TextAlign.Center,
            color = Color.Red,
            fontWeight = FontWeight.Bold)
    }
}