package com.hero.simpletodo.ui.addedittask

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hero.simpletodo.ui.taskslist.TasksListViewModel

@Composable
fun AddEditTaskScreen(
    navController: NavController,
    addEditTaskViewModel: AddEditTaskViewModel = hiltViewModel(),
    tasksListViewModel: TasksListViewModel,
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = addEditTaskViewModel.titleTextFieldState.value,
                onValueChange = {
                    addEditTaskViewModel.titleTextFieldState.value = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        text = "Title"
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = addEditTaskViewModel.descriptionTextFieldState.value,
                onValueChange = {
                    addEditTaskViewModel.descriptionTextFieldState.value = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        text = "Description"
                    )
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    addEditTaskViewModel.insert {
                        tasksListViewModel.refresh()
                    }
                    navController.popBackStack()
                }
            ) {
                Text(text = "Done")
            }
        }
    }
}