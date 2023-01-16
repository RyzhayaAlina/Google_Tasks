package com.example.google_tasks

import com.example.google_tasks.models.task.InDatabaseTaskRepository

class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()

        InDatabaseTaskRepository.initialize(this)
    }

}