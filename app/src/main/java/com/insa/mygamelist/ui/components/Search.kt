package com.insa.mygamelist.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults.InputField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.insa.mygamelist.data.ViewModels.GamesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSearchBar(
    navController: NavController,
    viewModel: GamesViewModel = viewModel()
) {
    // Search parameters
    var query by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val state = viewModel.gamesState

    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        inputField = {
            InputField(
                query = query,
                onQueryChange = { query = it },
                onSearch = {
                    viewModel.searchGameByName(query)
                },
                expanded = expanded,
                onExpandedChange = {
                    expanded = it
                },
                placeholder = { Text("Search for game...") },
                trailingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            )
        },
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        // Results of the search
        val results = state.games.filter {
            it.name.contains(query, ignoreCase = true)
        }
        if (results.isEmpty()) {
            Text(modifier = Modifier.padding(20.dp), text = "No results found")
        } else {
            VideoGameCardList(navController, results)
        }

    }

}