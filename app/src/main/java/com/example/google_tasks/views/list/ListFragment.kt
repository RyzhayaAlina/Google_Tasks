package com.example.google_tasks.views.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.google_tasks.databinding.FragmentListBinding
import com.example.google_tasks.models.task.Task

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val adapter = ListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialRecyclerView()
    }

    private fun initialRecyclerView() {
        binding.tasksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.tasksRecyclerView.adapter = adapter

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
        adapter.tasks = list
    }

}