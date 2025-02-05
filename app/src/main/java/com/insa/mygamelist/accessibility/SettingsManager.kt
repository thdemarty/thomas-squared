package com.insa.mygamelist.accessibility
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsManager(private val context: Context) {

    companion object {
        val HIGH_CONTRAST = booleanPreferencesKey("high_contrast")
        val BOLD_TEXT = booleanPreferencesKey("bold_text")
        val REDUCE_ANIMATIONS = booleanPreferencesKey("reduce_animations")
    }

    val highContrast: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[HIGH_CONTRAST] ?: false }

    val boldText: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[BOLD_TEXT] ?: false }

    val reduceAnimations: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[REDUCE_ANIMATIONS] ?: false }

    suspend fun setHighContrast(enabled: Boolean) {
        context.dataStore.edit { it[HIGH_CONTRAST] = enabled }
    }

    suspend fun setBoldText(enabled: Boolean) {
        context.dataStore.edit { it[BOLD_TEXT] = enabled }
    }

    suspend fun setReduceAnimations(enabled: Boolean) {
        context.dataStore.edit { it[REDUCE_ANIMATIONS] = enabled }
    }
}