package com.insa.mygamelist.ui.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.insa.mygamelist.R
import com.insa.mygamelist.data.Cover
import com.insa.mygamelist.data.Game
import com.insa.mygamelist.data.Genre
import com.insa.mygamelist.data.Platform
import com.insa.mygamelist.data.PlatformLogo

import java.util.StringJoiner


class GamePreviewParameterProvider : PreviewParameterProvider<Game> {
    override val values = sequenceOf(
        Game(
            id = 28278, cover = Cover(
                id = 97034, url = "//images.igdb.com/igdb/image/upload/t_cover_big/co22ve.jpg"
            ), firstReleaseDate = 0, genres = listOf(
                Genre(id = 15, name = "Strategy"),
                Genre(id = 26, name = "Quiz/Trivia"),
                Genre(id = 35, name = "Card & Board Game")
            ), name = "Catan Universe", platforms = listOf(
                Platform(
                    id = 6, name = "PC (Microsoft Windows)", logo = PlatformLogo(
                        id = 670, url = "//images.igdb.com/igdb/image/upload/t_logo_med/plim.jpg"
                    )
                ),
                Platform(
                    id = 39, name = "iOS", logo = PlatformLogo(
                        id = 248, url = "//images.igdb.com/igdb/image/upload/t_logo_med/pl6w.jpg"
                    )
                ),
                Platform(
                    id = 14, name = "Mac", logo = PlatformLogo(
                        id = 867, url = "//images.igdb.com/igdb/image/upload/t_logo_med/plo3.jpg"
                    )
                ),
            ), summary = "summary lorem ipsum ", totalRating = 97.13895178905847
        )
    )
}


@Preview
@Composable
fun VideoGameCard(
    modifier: Modifier = Modifier, @PreviewParameter(GamePreviewParameterProvider::class) game: Game
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
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