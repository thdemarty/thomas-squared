package com.insa.mygamelist.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = FavoritesRepository(application)

    private val _favorites = MutableStateFlow<Map<Long, Boolean>>(emptyMap())
    val favorites = _favorites.asStateFlow()

    // Load favorite state
    fun loadFavorite(gameId: Long) {
        viewModelScope.launch {
            repository.isFavorite(gameId).collectLatest { isFavorite ->
                _favorites.value += (gameId to isFavorite)
            }
        }
    }

    // Toggle favorite state
    fun toggleFavorite(gameId: Long) {
        val currentState = _favorites.value[gameId] ?: false
        viewModelScope.launch {
            repository.setFavorite(gameId, !currentState)
            _favorites.value += (gameId to !currentState)
        }
    }
}
