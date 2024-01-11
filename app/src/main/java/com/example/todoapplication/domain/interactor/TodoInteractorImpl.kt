package com.example.todoapplication.domain.interactor

import com.example.todoapplication.domain.entity.Todo
import com.example.todoapplication.domain.repository.TodoRepository
import javax.inject.Inject

class TodoInteractorImpl @Inject constructor(
    private val todoRepository: TodoRepository
) : TodoInteractor {
    override suspend fun getAllTodos(): List<Todo> = todoRepository.getAllTodos()

    override suspend fun getTodoById(id: Int): Todo = todoRepository.getTodoById(id)

    override suspend fun deleteTodo(todo: Todo) = todoRepository.deleteTodo(todo)

    override suspend fun updateTodo(todo: Todo) = todoRepository.updateTodo(todo)

    override suspend fun insertTodo(todo: Todo) = todoRepository.insertTodo(todo)
}