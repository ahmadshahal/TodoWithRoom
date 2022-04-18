package com.hero.simpletodo.ui.addedittask

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.simpletodo.data.databases.entities.Task
import com.hero.simpletodo.data.repositories.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val tasksRepository: TasksRepository,
    savedStateHandle: SavedStateHandle,
) :
    ViewModel() {
    val titleTextFieldState = mutableStateOf("")
    val descriptionTextFieldState = mutableStateOf("")
    private var task: Task? = null

    init {
        val taskId: Int = savedStateHandle.get<String>("taskId")?.toInt() ?: -1
        if(taskId != -1) {
            viewModelScope.launch {
                task = tasksRepository.getById(taskId).apply {
                    titleTextFieldState.value = this.title
                    descriptionTextFieldState.value = this.description ?: ""
                }
            }
        }
    }

    fun insert(onSuccess: suspend () -> Unit) {
        viewModelScope.launch {
            tasksRepository.insert(
                Task(
                    id = task?.id ?: 0,
                    title = titleTextFieldState.value,
                    description = descriptionTextFieldState.value,
                    done = task?.done ?: false
                )
            )
            onSuccess()
        }
    }
}