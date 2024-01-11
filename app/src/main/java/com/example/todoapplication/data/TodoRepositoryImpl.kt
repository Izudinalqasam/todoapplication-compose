package com.example.todoapplication.data

import com.example.todoapplication.data.datasource.TodoLocalDataSource
import com.example.todoapplication.data.mapper.TodoMapper
import com.example.todoapplication.domain.entity.Todo
import com.example.todoapplication.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDataSource: TodoLocalDataSource,
    private val todoMapper: TodoMapper
) : TodoRepository {
    override suspend fun getAllTodos(): List<Todo> {
        val todoEntityList = todoDataSource.getAllTodos()
        return todoEntityList.map { todoMapper.mapTodoEntityToTodo(it) }
    }

    override suspend fun getTodoById(id: Int): Todo {
        val todoEntity = todoDataSource.getTodoById(id)
        return todoMapper.mapTodoEntityToTodo(todoEntity)
    }

    override suspend fun deleteTodo(todo: Todo) {
       return todoDataSource.deleteTodo(
           todoMapper.mapTodoToTodoEntity(todo)
       )
    }

    override suspend fun updateTodo(todo: Todo) {
        return todoDataSource.updateTodo(
            todoMapper.mapTodoToTodoEntity(todo)
        )
    }

    override suspend fun insertTodo(todo: Todo) {
        return todoDataSource.insertTodo(
            todoMapper.mapTodoToTodoEntity(todo)
        )
    }
}