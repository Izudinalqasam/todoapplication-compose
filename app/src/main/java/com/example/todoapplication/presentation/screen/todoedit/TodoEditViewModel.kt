package com.example.todoapplication.presentation.screen.todoedit

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
class TodoEditViewModel @Inject constructor(
    private val todoInteractor: TodoInteractor
) : ViewModel() {

    private val _uiState: MutableStateFlow<UIState<Todo>> = MutableStateFlow(
        UIState.Initial
    )
    private val _closeState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val uiState = _uiState
    val closeState = _closeState

    fun getTodoById(id: Int) {
        viewModelScope.launch {
            _uiState.value = UIState.Loading

            val result = withContext(Dispatchers.IO) {
                todoInteractor.getTodoById(id)
            }

            _uiState.value = UIState.Success(result)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            _uiState.value = UIState.Loading

            withContext(Dispatchers.IO) {
                todoInteractor.deleteTodo(todo)
            }

            _uiState.value = UIState.Success(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            _uiState.value = UIState.Loading

            withContext(Dispatchers.IO) {
                todoInteractor.updateTodo(todo)

            }

            _uiState.value = UIState.Success(todo)
            _closeState.value = true
        }
    }
}