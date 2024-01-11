package com.example.todoapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapplication.data.model.TodoEntity

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private const val DATABASE_NAME = "todo_db"

        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    TodoDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}