package com.example.google_tasks.views.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.google_tasks.R
import com.example.google_tasks.databinding.TaskItemBinding
import com.example.google_tasks.models.task.Task

interface ListItemListener {

    fun updateTask(task: Task)

    fun showDetail(task: Task)

}

class ListAdapter(private val listener: ListItemListener) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    var tasks = mutableListOf<Task>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = TaskItemBinding.bind(view)

        fun bind(task: Task, listener: ListItemListener) {
            binding.completedCheckBox.isChecked = task.isCompleted
            binding.taskNameTextView.text = task.name
            binding.taskAdditionalTextView.text = task.additInfo

            binding.root.setOnClickListener {
                listener.showDetail(task)
            }

            if (task.isChosen) {
                binding.chosenImageButton.setImageResource(R.drawable.ic_chosen_full)
            } else {
                binding.chosenImageButton.setImageResource(R.drawable.ic_chosen_border)
            }

            binding.completedCheckBox.setOnClickListener {
                task.isCompleted = !task.isCompleted
                listener.updateTask(task)
            }
            binding.chosenImageButton.setOnClickListener {
                task.isChosen = !task.isChosen
                if (task.isChosen) {
                    binding.chosenImageButton.setImageResource(R.drawable.ic_chosen_full)
                } else {
                    binding.chosenImageButton.setImageResource(R.drawable.ic_chosen_border)
                }
                listener.updateTask(task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(tasks[position], listener)
    }

    override fun getItemCount(): Int = tasks.size

}