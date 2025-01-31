package com.insa.mygamelist.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension function for DataStore instance
private val Context.dataStore by preferencesDataStore(name = "favorites_store")

class FavoritesRepository(context: Context) {
    private val dataStore = context.dataStore
    private val FAVORITE_KEY_PREFIX = "favorite_game_"

    // Save favorite state
    suspend fun setFavorite(gameId: Long, isFavorite: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey("$FAVORITE_KEY_PREFIX$gameId")] = isFavorite
        }
    }

    // Get favorite state as a Flow
    fun isFavorite(gameId: Long): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey("$FAVORITE_KEY_PREFIX$gameId")] ?: false
        }
    }
}
