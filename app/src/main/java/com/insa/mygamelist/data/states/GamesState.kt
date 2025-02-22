package com.insa.mygamelist.data.states

import com.insa.mygamelist.data.models.Game

data class GamesState(
    val games: List<Game> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val hasMore: Boolean = true,
    val offset: Int = 0,
    val limit: Int = 20,
    val searchQuery: String = ""
) {
    // getGenres (flatten list of all genres)
    val genres: List<String> = games.flatMap { game -> game.genres.map { it.name } }.distinct()
}
