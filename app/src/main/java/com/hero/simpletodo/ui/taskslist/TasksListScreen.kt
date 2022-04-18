package com.hero.simpletodo.ui.taskslist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hero.simpletodo.data.databases.entities.Task
import com.hero.simpletodo.ui.taskslist.components.TaskItem

@Composable
fun TasksListScreen(viewModel: TasksListViewModel = hiltViewModel(), navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("/AddEditTaskScreen/${-1}")
                }
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "")
            }
        }
    ) {
        LazyColumn(contentPadding = PaddingValues(vertical = 16.dp)) {
            items(
                viewModel.tasksListState.value
            ) { task ->
                TaskItem(
                    task = task,
                    onClick = { navController.navigate("/AddEditTaskScreen/${task.id}") })
            }
        }
    }
}