package com.insa.mygamelist.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun SearchChip(
    label: String,
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        FilterChip(
            selected = expanded,
            onClick = { expanded = !expanded },
            trailingIcon = {
                if (!expanded) Icon(Icons.Default.ArrowDropDown, contentDescription = "Close")
                else Icon(Icons.Default.ArrowDropUp, contentDescription = "Close")
            },
            label = { Text(label) },
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Option 1") },
                onClick = { /* Handle option 1 click */ }
            )
            DropdownMenuItem(
                text = { Text("Option 2") },
                onClick = { /* Handle option 2 click */ }
            )
        }
    }
}