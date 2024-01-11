package com.example.todoapplication.data.datasource

import com.example.todoapplication.data.database.TodoDao
import com.example.todoapplication.data.database.TodoDatabase
import com.example.todoapplication.data.model.TodoEntity
import javax.inject.Inject

class TodoLocalDataSource @Inject constructor(
    private val database: TodoDao
) {

    suspend fun getAllTodos() = database.getAllTodos()

    suspend fun getTodoById(id: Int) = database.getTodoById(id)

    suspend fun deleteTodo(todoEntity: TodoEntity) {
        database.deleteTodo(todoEntity)
    }

    suspend fun updateTodo(
        todoEntity: TodoEntity
    ) = database.updateTodo(
        todoEntity
    )

    suspend fun insertTodo(
        todoEntity: TodoEntity
    ) = database.insertTodo(
        todoEntity
    )

}