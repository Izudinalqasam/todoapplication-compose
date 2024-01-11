package com.example.todoapplication.presentation.screen.todoadd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.domain.entity.Todo
import com.example.todoapplication.domain.interactor.TodoInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoAddViewModel @Inject constructor(
    private val todoInteractor: TodoInteractor
) : ViewModel() {

    fun insertTodoById(todo: Todo) {
        viewModelScope.launch {
            todoInteractor.insertTodo(todo)
        }
    }
}