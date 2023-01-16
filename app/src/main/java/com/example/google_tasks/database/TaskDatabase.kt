package com.example.google_tasks.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.google_tasks.models.task.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun getDao(): TaskDao
}