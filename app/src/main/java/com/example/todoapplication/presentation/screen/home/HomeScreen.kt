package com.example.todoapplication.presentation.screen.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    LazyColumn {

        item {
            Text(text = "Todo Application")
        }

       items(100) { index ->
            Text(text = "Item: $index")
        }
    }
}