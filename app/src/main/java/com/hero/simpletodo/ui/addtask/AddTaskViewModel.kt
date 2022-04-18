package com.hero.simpletodo.ui.addtask

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.simpletodo.data.databases.entities.Task
import com.hero.simpletodo.data.repositories.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val tasksRepository: TasksRepository,
) :
    ViewModel() {
    val titleTextFieldState = mutableStateOf("")
    val descriptionTextFieldState = mutableStateOf("")

    fun insert(callBack: suspend () -> Unit) {
        viewModelScope.launch {
            tasksRepository.insert(
                Task(
                    title = titleTextFieldState.value,
                    description = descriptionTextFieldState.value
                )
            )
            callBack()
        }
    }
}