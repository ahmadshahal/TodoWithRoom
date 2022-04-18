package com.hero.simpletodo.data.repositories


import com.hero.simpletodo.data.databases.AppDatabase
import com.hero.simpletodo.data.databases.entities.Task
import javax.inject.Inject

class TasksRepository @Inject constructor(private val tasksDb: AppDatabase) {
    suspend fun insert(task: Task) {
        tasksDb.tasksDao().insert(task)
    }
    suspend fun delete(task: Task) {
        tasksDb.tasksDao().delete(task)
    }
    suspend fun getById(id: Int) : Task {
        return tasksDb.tasksDao().getById(id)
    }
    suspend fun getAll(): List<Task> {
        return tasksDb.tasksDao().getAll()
    }
    suspend fun update(task: Task) {
        tasksDb.tasksDao().update(task)
    }
}