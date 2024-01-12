package com.example.todoapplication.presentation.navigation

sealed class TodoNavigation(val route: String) {
    object Home : TodoNavigation("home")
    object TodoEdit : TodoNavigation("todo_edit/{todoId}")
    object TodoAdd : TodoNavigation("todo_add")
}
