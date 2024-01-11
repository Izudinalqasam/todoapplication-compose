package com.example.todoapplication.di

import android.content.Context
import com.example.todoapplication.data.database.TodoDao
import com.example.todoapplication.data.database.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDao(todoDatabase: TodoDatabase): TodoDao {
        return todoDatabase.todoDao()
    }

    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context): TodoDatabase {
        return TodoDatabase.getInstance(context)
    }
}