package com.example.todoapplication.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todoapplication.data.model.TodoEntity

@Dao
interface TodoDao {

    @Query("SELECT * FROM TodoEntity")
    suspend fun getAllTodos(): List<TodoEntity>

    @Query("SELECT * FROM TodoEntity WHERE id = :id")
    suspend fun getTodoById(id: Int): TodoEntity

    @Update
    suspend fun updateTodo(todoEntity: TodoEntity)

    @Delete
    suspend fun deleteTodo(todo: TodoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoEntity)
}
