package com.insa.mygamelist.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.insa.mygamelist.accessibility.AccessibilityViewModel

@Composable
fun AccessibilitySettingsScreen( navController: NavController, viewModel: AccessibilityViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val highContrast by viewModel.highContrast.collectAsState()
    val boldText by viewModel.boldText.collectAsState()
    val reduceAnimations by viewModel.reduceAnimations.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Paramètres d'accessibilité", style = MaterialTheme.typography.headlineMedium)

        // High contrast mode
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("High contrast mode")
            Switch(checked = highContrast, onCheckedChange = { viewModel.toggleHighContrast() })
        }

        // Bold Text Mode
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Bold Text Mode")
            Switch(checked = boldText, onCheckedChange = { viewModel.toggleBoldText() })
        }

        // Fewer Animations Mode
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Fewer Animations Mode")
            Switch(checked = reduceAnimations, onCheckedChange = { viewModel.toggleReduceAnimations() })
        }
    }
}