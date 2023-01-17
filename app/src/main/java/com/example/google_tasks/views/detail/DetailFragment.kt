package com.example.google_tasks.views.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.google_tasks.databinding.FragmentTaskDetailBinding
import java.util.*

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentTaskDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        return binding.root
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