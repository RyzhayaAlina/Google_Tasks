package com.example.google_tasks.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.google_tasks.models.task.Task
import java.util.UUID

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAll(): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE id=(:id)")
    fun getById(id: UUID): LiveData<Task?>

    @Insert
    suspend fun add(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

}