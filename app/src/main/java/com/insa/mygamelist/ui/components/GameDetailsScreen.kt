package com.insa.mygamelist.ui.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.snapTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.insa.mygamelist.R
import com.insa.mygamelist.data.IGDB
import kotlinx.coroutines.launch
import java.util.StringJoiner
import kotlin.math.roundToInt

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameDetailScreen(
    navController: NavController,
    gameId: Long
) {
    if (gameId > -1L) {
        val game = IGDB.games.find { it.id == gameId }
        val gameIndex = IGDB.games.indexOf(game)

        val nextGameId = if (gameIndex in 0 until IGDB.games.size - 1) IGDB.games[gameIndex + 1].id else null
        val prevGameId = if (gameIndex > 0) IGDB.games[gameIndex - 1].id else null
        val anchors = DraggableAnchors {
            -1f at -450f // Swipe left (go to next game), Keep "-" signs
            0f at 0f     // Center (current game)
            1f at 450f   // Swipe right (go to previous game)
        }

        val draggableState = remember {
            AnchoredDraggableState(
                initialValue = 0f,
                anchors = anchors,
                positionalThreshold = { distance -> distance * 0.5f },
                velocityThreshold = { 500f },
                snapAnimationSpec = spring(),
                decayAnimationSpec = exponentialDecay()
            )
        }

        val offsetX = draggableState.offset
        val coroutineScope = rememberCoroutineScope()

        // Detect swipe action
        LaunchedEffect(draggableState.currentValue) {
            Log.d("SwipeDebug", "Current Offset: $offsetX")
            when (draggableState.currentValue) {
                -1f -> if (nextGameId != null) {
                    coroutineScope.launch { draggableState.snapTo(0f) }
                    navController.navigate(GameDetailView(id = nextGameId))
                }
                1f -> if (prevGameId != null) {
                    coroutineScope.launch { draggableState.snapTo(0f) }
                    navController.navigate(GameDetailView(id = prevGameId))
                }
            }
        }

        if (game != null){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .anchoredDraggable(
                        state = draggableState,
                        orientation = Orientation.Horizontal
                    )
                    .offset { IntOffset(offsetX.roundToInt(), 0) }
            ) {
                LazyColumn(
                    modifier = Modifier
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
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .padding(top = 20.dp),
                                model = url,
                                alignment = Alignment.Center,
                                contentDescription = game.name
                            )
                        }
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        ) {
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
                                /* Put a platform logo inside a surface */
                                Surface(
                                    modifier = Modifier.height(80.dp).width(80.dp).padding(horizontal = 6.dp),
                                    color = Color.Black,
                                ) {
                                    AsyncImage(
                                        modifier = Modifier
                                            .height(70.dp)
                                            .width(70.dp)
                                            .padding(5.dp),
                                        model = url,
                                        contentDescription = platform.name,
                                        error = painterResource(id = R.drawable.no_photo_2)
                                    )
                                }

                            }
                        }
                        Row {
                            Text(text = game.summary, textAlign = TextAlign.Justify)
                        }
                    }
                }
            }
        }


    } else {
        Text(
            text = "No game id for this game",
            textAlign = TextAlign.Center,
            color = Color.Red,
            fontWeight = FontWeight.Bold
        )
    }
}
