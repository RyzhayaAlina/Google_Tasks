package com.example.google_tasks.views.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.google_tasks.R
import com.example.google_tasks.databinding.TaskItemBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    var tasks = mutableListOf<Int>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = TaskItemBinding.bind(view)

        fun bind(task: Int) {
            binding.completedCheckBox.isChecked = false
            binding.taskNameTextView.text = task.toString()
            binding.taskAdditionalTextView.text = "addit"
            binding.chosenImageButton.setImageResource(R.drawable.ic_chosen_border)
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