package com.hero.simpletodo.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hero.simpletodo.data.databases.daos.TaskDao
import com.hero.simpletodo.data.databases.entities.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tasksDao(): TaskDao
}