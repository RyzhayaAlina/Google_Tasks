package com.example.google_tasks.views.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.google_tasks.R
import com.example.google_tasks.databinding.CreateTaskBottomDialogBinding
import com.example.google_tasks.databinding.FragmentListBinding
import com.example.google_tasks.models.task.Task
import com.example.google_tasks.views.detail.DetailFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

class ListFragment : Fragment(), ListItemListener {

    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by activityViewModels()
    private val adapter = ListAdapter(this)
    private lateinit var createTaskBottomDialog: BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)


        createTaskBottomDialog = BottomSheetDialog(requireContext(), R.style.BottomDialogStyle)
        createTaskBottomDialog.window?.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        createBottomDialog()

        binding.createTaskFloatingButton.setOnClickListener {
            createTaskBottomDialog.show()
        }

        return binding.root
    }

    private fun createBottomDialog() {
        val dialogBinding = CreateTaskBottomDialogBinding.inflate(LayoutInflater.from(requireContext()), null, false)

        createTaskBottomDialog.setContentView(dialogBinding.root)

        createTaskBottomDialog.setOnDismissListener {
            dialogBinding.taskNameEditText.text.clear()
            dialogBinding.taskAdditEditText.text.clear()
            dialogBinding.taskAdditEditText.visibility = View.GONE
            dialogBinding.addToChosenCheckBox.isChecked = false
        }

        dialogBinding.taskNameEditText.requestFocus()

        dialogBinding.showAdditImageButton.setOnClickListener {
            dialogBinding.taskAdditEditText.visibility = View.VISIBLE
        }

        dialogBinding.addToChosenCheckBox.setOnCheckedChangeListener { _, isChecked ->
            dialogBinding.addToChosenCheckBox.setButtonDrawable(
                if (isChecked) R.drawable.ic_chosen_full else R.drawable.ic_chosen_border
            )
        }

        dialogBinding.saveButton.setOnClickListener {
            val task = Task(
                name = dialogBinding.taskNameEditText.text.toString(),
                additInfo = dialogBinding.taskAdditEditText.text.toString(),
                isChosen = dialogBinding.addToChosenCheckBox.isChecked
            )
            viewModel.createTask(task)
            createTaskBottomDialog.dismiss()
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialRecyclerView()

        viewModel.tasks.observe(viewLifecycleOwner) {
            adapter.tasks = it.toMutableList()
        }
    }

    private fun initialRecyclerView() {
        binding.tasksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.tasksRecyclerView.adapter = adapter
    }

    override fun updateTask(task: Task) {
        viewModel.updateTask(task)
    }

    override fun showDetail(task: Task) {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, DetailFragment.newInstance(task.id))
            .commit()
    }

}