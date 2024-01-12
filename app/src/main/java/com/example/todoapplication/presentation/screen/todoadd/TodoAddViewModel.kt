package com.example.todoapplication.presentation.screen.todoadd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.domain.entity.Todo
import com.example.todoapplication.domain.interactor.TodoInteractor
import com.example.todoapplication.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TodoAddViewModel @Inject constructor(
    private val todoInteractor: TodoInteractor
) : ViewModel() {

    private val _uiState: MutableStateFlow<UIState<String>> = MutableStateFlow(UIState.Initial)
    val uiState = _uiState

    fun insertTodoById(todo: Todo) {
        viewModelScope.launch {
            _uiState.value = UIState.Loading

            withContext(Dispatchers.IO) {
                todoInteractor.insertTodo(todo)
            }
            _uiState.value = UIState.Success("Todo added successfully")
        }
    }

    fun resetUIState() {
        _uiState.value = UIState.Initial
    }
}