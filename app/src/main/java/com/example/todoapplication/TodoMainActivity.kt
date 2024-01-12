package com.example.todoapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapplication.presentation.navigation.TodoNavigation
import com.example.todoapplication.presentation.screen.home.HomeScreen
import com.example.todoapplication.presentation.screen.home.HomeViewModel
import com.example.todoapplication.presentation.screen.todoadd.TodoAddScreen
import com.example.todoapplication.presentation.screen.todoadd.TodoAddViewModel
import com.example.todoapplication.presentation.screen.todoedit.TodoEditScreen
import com.example.todoapplication.presentation.screen.todoedit.TodoEditViewModel
import com.example.todoapplication.presentation.theme.TodoApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoMainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppMainScreen()
                }
            }
        }
    }
}

@Composable
fun AppMainScreen() {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val todoAddViewModel = hiltViewModel<TodoAddViewModel>()
    val todoEditViewModel = hiltViewModel<TodoEditViewModel>()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = TodoNavigation.Home.route
    ) {
        composable(TodoNavigation.Home.route) {
            HomeScreen(homeViewModel)
        }

        composable(TodoNavigation.TodoAdd.route) {
            TodoAddScreen(navController, todoAddViewModel)
        }

        composable(
            route = TodoNavigation.TodoEdit.route,
            arguments = listOf(navArgument("todoId") { type = NavType.StringType })
        ) {
            TodoEditScreen(navController, todoEditViewModel, it.arguments?.getInt("todoId") ?: 0)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoApplicationTheme {
        AppMainScreen()
    }
}