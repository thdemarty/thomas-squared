package com.insa.mygamelist.accessibility

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AccessibilityViewModel(application: Application) : AndroidViewModel(application) {
    private val settingsManager = SettingsManager(application)

    private val _highContrast = MutableStateFlow(false)
    val highContrast: StateFlow<Boolean> = _highContrast

    private val _boldText = MutableStateFlow(false)
    val boldText: StateFlow<Boolean> = _boldText

    private val _reduceAnimations = MutableStateFlow(false)
    val reduceAnimations: StateFlow<Boolean> = _reduceAnimations

    init {
        viewModelScope.launch {
            settingsManager.highContrast.collect { _highContrast.value = it }
        }
        viewModelScope.launch {
            settingsManager.boldText.collect { _boldText.value = it }
        }
        viewModelScope.launch {
            settingsManager.reduceAnimations.collect { _reduceAnimations.value = it }
        }
    }

    fun toggleHighContrast() = viewModelScope.launch {
        settingsManager.setHighContrast(!_highContrast.value)
    }

    fun toggleBoldText() = viewModelScope.launch {
        settingsManager.setBoldText(!_boldText.value)
    }

    fun toggleReduceAnimations() = viewModelScope.launch {
        settingsManager.setReduceAnimations(!_reduceAnimations.value)
    }
}
