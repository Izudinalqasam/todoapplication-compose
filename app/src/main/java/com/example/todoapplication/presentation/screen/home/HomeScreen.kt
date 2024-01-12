package com.example.todoapplication.presentation.screen.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.todoapplication.presentation.state.UIState

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    val uiState = homeViewModel.uiState.collectAsState()

    when(uiState.value) {
        is UIState.Initial -> {
            homeViewModel.getAllTodos()
        }
        is UIState.Loading -> {
            Text(text = "Loading...")
        }
        is UIState.Success -> {
            val todos = (uiState.value as UIState.Success).data
            LazyColumn {

                item {
                    Text(text = "Todo Application")
                }
                items(items = todos) {
                    Text(text = it.title)
                }
            }
        }
        is UIState.Error -> {
            val errorMessage = (uiState.value as UIState.Error).message
            Text(text = errorMessage)
        }
    }
}