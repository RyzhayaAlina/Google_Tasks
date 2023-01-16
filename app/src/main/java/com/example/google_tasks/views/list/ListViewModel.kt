package com.example.google_tasks.views.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.google_tasks.models.task.Task

class ListViewModel : ViewModel() {

    val tasks = MutableLiveData<List<Task>>()

    fun fetchData() {
        val list = mutableListOf<Task>()
        list.add(Task(
            name = "task 0",
            additInfo = "addit info",
            isChosen = true))
        for (i in 1..10) {
            list.add(Task(
                name = "task $i",
                additInfo = "",
                isChosen = false))
        }
        tasks.value = list
    }

}