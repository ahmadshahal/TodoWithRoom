package com.hero.simpletodo.data.databases.daos

import androidx.room.*
import com.hero.simpletodo.data.databases.entities.Task

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM Task")
    suspend fun getAll() : List<Task>

    @Update
    suspend fun update(task: Task)
}