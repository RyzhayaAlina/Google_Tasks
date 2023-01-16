package com.example.google_tasks.models.task

import android.content.Context
import androidx.room.Room
import com.example.google_tasks.database.TaskDatabase
import java.util.*

class InDatabaseTaskRepository private constructor(context: Context) {

    private val database: TaskDatabase = Room.databaseBuilder(
        context.applicationContext,
        TaskDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val taskDao = database.getDao()

    fun getAll(): List<Task> {
        return taskDao.getAll()
    }

    fun getById(id: UUID): Task? {
        return taskDao.getById(id)
    }

    suspend fun add(task: Task) {
        taskDao.add(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }


    companion object {

        private const val DATABASE_NAME = "task-database"

        private val INSTANCE: InDatabaseTaskRepository? = null

        fun get(): InDatabaseTaskRepository {
            return INSTANCE ?: throw IllegalStateException("InDatabaseTaskRepository must be initialize")
        }

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                InDatabaseTaskRepository(context)
            }
        }

    }

}