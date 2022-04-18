package com.hero.simpletodo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hero.simpletodo.ui.addtask.AddTaskScreen
import com.hero.simpletodo.ui.taskslist.TasksListScreen
import com.hero.simpletodo.ui.taskslist.TasksListViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "/TasksListScreen") {
        composable("/TasksListScreen") {
            TasksListScreen(navController = navController)
        }
        composable("/AddTaskScreen") {
            val parentEntry = remember(it) {
                navController.getBackStackEntry("/TasksListScreen")
            }
            val tasksListViewModel: TasksListViewModel = hiltViewModel(parentEntry)
            AddTaskScreen(navController = navController, tasksListViewModel = tasksListViewModel)
        }
    }
}