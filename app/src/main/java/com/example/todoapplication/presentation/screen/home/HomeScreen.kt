package com.example.todoapplication.presentation.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapplication.presentation.navigation.TodoNavigation
import com.example.todoapplication.presentation.state.UIState

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    val uiState = homeViewModel.uiState.collectAsState()

    when (uiState.value) {
        is UIState.Initial -> {
            homeViewModel.getAllTodos()
        }

        is UIState.Loading -> {
            Text(text = "Loading...")
        }

        is UIState.Success -> {
            val todos = (uiState.value as UIState.Success).data
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp)
            ) {

                item {
                    Text(
                        text = "Todo Application",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
                items(items = todos) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    "todo_edit/${it.id}"
                                )
                            }
                    ) {
                        Text(
                            text = it.title.uppercase(),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W900,
                            modifier = Modifier
                                .padding(10.dp, 10.dp, 10.dp, 0.dp)
                        )
                        Text(text = it.description, modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp))
                    }
                }

                item {
                    OutlinedButton(
                        onClick = {
                            navController.navigate(TodoNavigation.TodoAdd.route)
                        },
                        modifier = Modifier.padding(vertical = 30.dp)
                    ) {
                        Text(text = "Add Todo", modifier = Modifier
                            .padding(vertical = 10.dp)
                            .clickable {
                                navController.navigate(TodoNavigation.TodoAdd.route)
                            })
                    }
                }
            }
        }

        is UIState.Error -> {
            val errorMessage = (uiState.value as UIState.Error).message
            Text(text = errorMessage)
        }
    }
}