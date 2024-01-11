package com.example.todoapplication.domain.interactor

import com.example.todoapplication.domain.entity.Todo

interface TodoInteractor {
    suspend fun getAllTodos(): List<Todo>

    suspend fun getTodoById(id: Int): Todo

    suspend fun deleteTodo(todo: Todo)

    suspend fun updateTodo(todo: Todo)

    suspend fun insertTodo(todo: Todo)

}