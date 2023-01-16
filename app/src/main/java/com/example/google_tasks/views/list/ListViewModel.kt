package com.example.google_tasks.views.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.google_tasks.models.task.InDatabaseTaskRepository
import com.example.google_tasks.models.task.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val taskRepository = InDatabaseTaskRepository.get()

    val tasks = taskRepository.getAll()

    fun createTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.add(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.update(task)
        }
    }

}