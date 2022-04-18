package com.hero.simpletodo.ui.taskslist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.simpletodo.data.databases.entities.Task
import com.hero.simpletodo.data.repositories.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksListViewModel @Inject constructor(private val tasksRepository: TasksRepository) :
    ViewModel() {

    private val _tasksListState: MutableState<List<Task>> = mutableStateOf(emptyList())
    val tasksListState: State<List<Task>> = _tasksListState

    init {
        viewModelScope.launch {
            refresh()
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            tasksRepository.delete(task)
            refresh()
        }
    }

    fun update(task: Task) {
        viewModelScope.launch {
            tasksRepository.update(task)
            refresh()
        }
    }

    suspend fun refresh() {
        _tasksListState.value = tasksRepository.getAll()
    }
}