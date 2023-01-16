package com.example.google_tasks.views.list

import androidx.lifecycle.ViewModel
import com.example.google_tasks.models.task.InDatabaseTaskRepository

class ListViewModel : ViewModel() {

    private val taskRepository = InDatabaseTaskRepository.get()

    val tasks = taskRepository.getAll()

}