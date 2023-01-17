package com.example.google_tasks.views.detail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.google_tasks.R
import com.example.google_tasks.databinding.FragmentTaskDetailBinding
import com.example.google_tasks.models.task.Task
import java.util.*

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentTaskDetailBinding
    private val viewModel: DetailVieModel by activityViewModels()
    private lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskId = requireArguments().getSerializable(ARG_TASK_ID) as UUID
        viewModel.loadTask(taskId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskDetailBinding.inflate(inflater, container, false)

        binding.backImageButton.setOnClickListener {
            //go back
        }

        binding.addInComletedButton.setOnClickListener {
            task.isCompleted = !task.isCompleted
            if (task.isCompleted) {
                viewModel.updateTask(task)
                //выхоод
            }
        }

        binding.deleteImageButton.setOnClickListener {
            viewModel.deleteTask()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.taskLiveData.observe(viewLifecycleOwner) {
            task = it
            updateScreen()
        }
    }

    override fun onStart() {
        super.onStart()
        val nameTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                task.name = s.toString()
            }
        }
        binding.nameEditText.addTextChangedListener(nameTextWatcher)

        val additTextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                task.name = s.toString()
            }
        }
        binding.additEditText.addTextChangedListener(additTextWatcher)
    }

    private fun updateScreen() {
        binding.nameEditText.setText(task.name)
        binding.additEditText.setText(task.additInfo)
        binding.chosenCheckBox.isChecked = task.isChosen
        binding.addInComletedButton.setText(if (task.isCompleted) R.string.mark_uncompleted else R.string.mark_completed)
    }

    companion object {

        private const val ARG_TASK_ID = "arg_task_id"

        fun newInstance(id: UUID): DetailFragment {
            val args = Bundle().apply {
                putSerializable(ARG_TASK_ID, id)
            }
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

}