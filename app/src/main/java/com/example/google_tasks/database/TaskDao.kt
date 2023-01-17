package com.example.google_tasks.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.google_tasks.models.task.Task
import java.util.UUID

@Dao
interface TaskDao {

    @Query("SELECT * FROM task WHERE isCompleted = 0")
    fun getAll(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE id=(:id)")
    fun getById(id: UUID): LiveData<Task?>

    @Query("SELECT * FROM task WHERE isChosen = 1 AND isCompleted = 0")
    fun getChosen(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE isCompleted = 1")
    fun getCompleted(): LiveData<List<Task>>

    @Insert
    suspend fun add(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

}