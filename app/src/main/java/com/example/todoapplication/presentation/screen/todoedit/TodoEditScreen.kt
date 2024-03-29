package com.example.todoapplication.presentation.screen.todoedit

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapplication.domain.entity.Todo
import com.example.todoapplication.presentation.state.UIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoEditScreen(
    navController: NavController,
    todoEditViewModel: TodoEditViewModel,
    todoId: Int
) {
    val uiState = todoEditViewModel.uiState.collectAsState()
    val clearState = todoEditViewModel.clearUIState.collectAsState()

    LaunchedEffect(todoId) {
        todoEditViewModel.getTodoById(todoId)
    }

    LaunchedEffect(key1 = clearState.value) {
        if (clearState.value) {
            navController.popBackStack()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            todoEditViewModel.resetUIState()
        }
    }


    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Edit Todo",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                if (uiState.value is UIState.Success && (uiState.value as UIState.Success<Todo>).data.id != -1) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Back",
                        modifier = Modifier.clickable {
                            todoEditViewModel.deleteTodo((uiState.value as UIState.Success<Todo>).data)
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when (uiState.value) {
                is UIState.Loading -> {
                    Text(text = "Loading...")
                }

                is UIState.Success -> {
                    val todo = (uiState.value as UIState.Success<Todo>).data
                    InputForm(todo, todoEditViewModel = todoEditViewModel)
                }

                is UIState.Error -> {
                    Text(text = "Error")
                }

                else -> {
                    Text(text = "Initial")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputForm(todo: Todo, todoEditViewModel: TodoEditViewModel) {
    var title by remember(todo.title) { mutableStateOf(todo.title) }
    var description by remember(todo.description) { mutableStateOf(todo.description) }
    var content by remember(todo.content) { mutableStateOf(todo.content) }

    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
            },
            label = {
                Text(text = "Title")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
            },
            label = {
                Text(text = "Description")
            }, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            singleLine = false, label = {
                Text(text = "Content")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            maxLines = 5,
            modifier = Modifier.height(100.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedButton(onClick = {
            todoEditViewModel.updateTodo(
                Todo(
                    id = todo.id,
                    title = title,
                    description = description,
                    content = content
                )
            )
            Toast.makeText(context, "Update berhasil", Toast.LENGTH_SHORT).show()
        }
        ) {
            Text(text = "Update")
        }
    }
}