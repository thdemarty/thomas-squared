package com.insa.mygamelist.data.ViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.insa.mygamelist.data.IGDB
import com.insa.mygamelist.data.models.Game
import com.insa.mygamelist.data.states.GamesState
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class GamesViewModel : ViewModel() {
    var gamesState by mutableStateOf(GamesState())
        private set
    var isTokenFetched by mutableStateOf(false)
        private set

    val filteredGames: List<Game>
        get() = if (gamesState.searchQuery.isBlank()) {
            gamesState.games
        } else {
            gamesState.games.filter {
                it.name.contains(gamesState.searchQuery, ignoreCase = true)
            }
        }

    init {
        fetchToken()
    }

    private fun fetchToken() {
        viewModelScope.launch {
            try {
                val response = IGDB.authApi.getAccessToken(
                    IGDB.CLIENT_ID,
                    IGDB.CLIENT_SECRET
                )
                if (response.isSuccessful) {
                    val tokenResponse = response.body()
                    tokenResponse?.let {
                        IGDB.setAccessToken(it.accessToken)
                        Log.d("GamesViewModel", "Access token ${it.accessToken}")
                        isTokenFetched = true
                        fetchGames()
                    }
                } else {
                    Log.e(
                        "GamesViewModel",
                        "Error fetching access token: ${response.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                Log.e("GamesViewModel", "Error fetching access token", e)
            }
        }
    }

    fun fetchGames() {
        if (gamesState.isLoading || !gamesState.hasMore) return
        gamesState = gamesState.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val query =
                    "fields cover.url,first_release_date,genres.name,name,platforms.name,platforms.platform_logo.url,summary,total_rating; " +
                            "limit ${gamesState.limit};" +
                            "offset ${gamesState.offset};" +
                            "where summary != null & total_rating != null & cover != null & platforms != null & first_release_date != null;"
                val body =
                    query.toRequestBody("application/x-www-form-urlencoded".toMediaTypeOrNull())

                val response = IGDB.igdbApi.getGames(body)

                if (response.isSuccessful) {
                    val newGames = response.body() ?: emptyList()
                    gamesState = gamesState.copy(
                        games = gamesState.games + newGames,
                        isLoading = false,
                        hasMore = newGames.size == gamesState.limit,
                        offset = gamesState.offset + newGames.size,
                        error = null,
                    )
                } else {
                    Log.d(
                        "GamesViewModel",
                        "1 (offset: ${gamesState.offset}) Error fetching games: $response"
                    )
                    gamesState = gamesState.copy(
                        isLoading = false,
                        error = "Error fetching games: ${response.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                Log.d("GamesViewModel", "2 (offset: ${gamesState.offset}) Error fetching games: $e")
                gamesState = gamesState.copy(
                    isLoading = false,
                    error = "Exception fetching games: ${e.message}"
                )
            }
        }
    }

    fun updateSearchQuery(query: String) {
        gamesState = gamesState.copy(searchQuery = query)
    }
}