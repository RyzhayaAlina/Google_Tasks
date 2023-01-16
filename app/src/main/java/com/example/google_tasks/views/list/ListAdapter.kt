package com.example.google_tasks.views.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.google_tasks.R
import com.example.google_tasks.databinding.TaskItemBinding
import com.example.google_tasks.models.task.Task

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    var tasks = mutableListOf<Task>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = TaskItemBinding.bind(view)

        fun bind(task: Task) {
            binding.completedCheckBox.isChecked = task.isCompleted
            binding.taskNameTextView.text = task.name
            binding.taskAdditionalTextView.text = task.additInfo

            if (task.isChosen) {
                binding.chosenImageButton.setImageResource(R.drawable.ic_chosen_full)
            } else {
                binding.chosenImageButton.setImageResource(R.drawable.ic_chosen_border)
            }

            binding.completedCheckBox.setOnCheckedChangeListener { _, isChecked ->
                task.isCompleted = isChecked
            }
            binding.chosenImageButton.setOnClickListener {
                task.isChosen = !task.isChosen
                if (task.isChosen) {
                    binding.chosenImageButton.setImageResource(R.drawable.ic_chosen_full)
                } else {
                    binding.chosenImageButton.setImageResource(R.drawable.ic_chosen_border)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

}