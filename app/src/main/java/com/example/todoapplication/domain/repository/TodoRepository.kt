package com.example.todoapplication.domain.repository

import com.example.todoapplication.domain.entity.Todo


interface TodoRepository {
    suspend fun getAllTodos(): List<Todo>

    suspend fun getTodoById(id: Int): Todo

    suspend fun deleteTodo(todo: Todo)

    suspend fun updateTodo(todo: Todo)

    suspend fun insertTodo(todo: Todo)
}