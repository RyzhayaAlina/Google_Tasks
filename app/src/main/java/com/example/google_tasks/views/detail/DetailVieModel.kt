package com.example.google_tasks.views.detail

import androidx.lifecycle.*
import com.example.google_tasks.models.task.InDatabaseTaskRepository
import com.example.google_tasks.models.task.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class DetailVieModel : ViewModel() {

    private val taskRepository = InDatabaseTaskRepository.get()

    private val taskIdLiveData = MutableLiveData<UUID>()

    val taskLiveData: LiveData<Task> = Transformations.switchMap(taskIdLiveData) {
        taskRepository.getById(it)
    }

    fun loadTask(taskId: UUID) {
        taskIdLiveData.value = taskId
    }

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.update(task)
        }
    }

    fun deleteTask() {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.delete(taskLiveData.value!!)
        }
    }

}