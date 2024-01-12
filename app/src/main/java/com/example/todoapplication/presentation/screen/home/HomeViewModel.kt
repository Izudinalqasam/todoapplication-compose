package com.example.todoapplication.presentation.screen.home

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
class HomeViewModel @Inject constructor(
    private val todoInteractor: TodoInteractor
) : ViewModel() {

    private val _uiState: MutableStateFlow<UIState<List<Todo>>> = MutableStateFlow(UIState.Initial)
    val uiState = _uiState

    fun getAllTodos() {
        viewModelScope.launch {
            _uiState.value = UIState.Loading

            try {
                val todos = withContext(Dispatchers.IO) {
                   todoInteractor.getAllTodos()
                }
                _uiState.value = UIState.Success(todos)
            } catch (e: Exception) {
                _uiState.value = UIState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }
}