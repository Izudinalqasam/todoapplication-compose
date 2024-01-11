package com.example.todoapplication.presentation.screen.todoedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.domain.entity.Todo
import com.example.todoapplication.domain.interactor.TodoInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoEditViewModel @Inject constructor(
    private val todoInteractor: TodoInteractor
): ViewModel() {

    fun getTodoById(id: Int) {
        viewModelScope.launch {
            todoInteractor.getTodoById(id)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoInteractor.deleteTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            todoInteractor.updateTodo(todo)

        }
    }
}