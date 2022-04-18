package com.hero.simpletodo.ui.taskslist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hero.simpletodo.data.databases.entities.Task
import com.hero.simpletodo.ui.taskslist.TasksListViewModel

@Composable
fun TaskItem(task: Task, onClick: () -> Unit, viewModel: TasksListViewModel = hiltViewModel()) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 10.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1.0F)
        ) {
            Text(text = task.title, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = task.description ?: "", color = Color.LightGray, fontSize = 13.sp)
        }
        Spacer(modifier = Modifier.width(5.dp))
        IconButton(onClick = {
            viewModel.deleteTask(task)
        }) {
            Icon(imageVector = Icons.Rounded.Delete, contentDescription = "")
        }
        Spacer(modifier = Modifier.width(5.dp))
        Checkbox(checked = task.done, onCheckedChange = { checked ->
            viewModel.update(task.copy(done = checked))
        })
    }
}