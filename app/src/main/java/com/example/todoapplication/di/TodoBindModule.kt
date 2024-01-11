package com.example.todoapplication.di

import com.example.todoapplication.data.TodoRepositoryImpl
import com.example.todoapplication.domain.interactor.TodoInteractor
import com.example.todoapplication.domain.interactor.TodoInteractorImpl
import com.example.todoapplication.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TodoBindModule {

    @Binds
    abstract fun bindTodoRepository(todoRepositoryImpl: TodoRepositoryImpl): TodoRepository

    @Binds
    abstract fun bindTodoIntractor(todoInteractorImpl: TodoInteractorImpl): TodoInteractor
}