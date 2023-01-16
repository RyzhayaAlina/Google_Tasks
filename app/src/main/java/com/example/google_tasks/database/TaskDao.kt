package com.example.google_tasks.database

import androidx.room.*
import com.example.google_tasks.models.task.Task
import java.util.UUID

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE id=(:id)")
    fun getById(id: UUID): Task?

    @Insert
    suspend fun add(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

}