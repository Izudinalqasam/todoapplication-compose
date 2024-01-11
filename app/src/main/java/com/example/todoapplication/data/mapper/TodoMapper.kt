package com.example.todoapplication.data.mapper

import com.example.todoapplication.data.model.TodoEntity
import com.example.todoapplication.domain.entity.Todo
import javax.inject.Inject

class TodoMapper @Inject constructor() {

    fun mapTodoEntityToTodo(todoEntity: TodoEntity): Todo{
        return Todo(
            id = todoEntity.id,
            title = todoEntity.title,
            description = todoEntity.description,
            content = todoEntity.content
        )
    }

    fun mapTodoToTodoEntity(todo: Todo): TodoEntity{
        return TodoEntity(
            id = todo.id,
            title = todo.title,
            description = todo.description,
            content = todo.content
        )
    }
}