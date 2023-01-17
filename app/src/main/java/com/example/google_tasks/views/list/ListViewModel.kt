package com.example.google_tasks.views.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.google_tasks.models.task.InDatabaseTaskRepository
import com.example.google_tasks.models.task.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val taskRepository = InDatabaseTaskRepository.get()

    val myTasks = taskRepository.getAll()
    val chosenTasks = taskRepository.getChosen()
    val completedTask = taskRepository.getCompleted()


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