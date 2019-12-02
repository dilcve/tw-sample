package com.rf.tw_sample.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.rf.tw_sample.databinding.FragmentTaskBinding
import com.rf.tw_sample.di.Injectable
import javax.inject.Inject

class TasksFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<TasksViewModel> { viewModelFactory }

    private val args: TasksFragmentArgs by navArgs()

    private lateinit var binding: FragmentTaskBinding

    private lateinit var adapter: TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.apply {
            this@apply.lifecycleOwner = viewLifecycleOwner
            this@apply.viewModel = this@TasksFragment.viewModel
        }
        binding.viewModel

        adapter = TasksAdapter {
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
        }

        binding.list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@TasksFragment.adapter
        }

        viewModel.loadTasksByProject(args.projectId)

        binding.errorView.setOnClickListener {
            viewModel.loadTasksByProject(args.projectId)
        }
        binding.errorMessage.setOnClickListener {
            viewModel.loadTasksByProject(args.projectId)
        }

        subscribe()
    }

    private fun subscribe() {
        viewModel.getTasksLiveData().observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        viewModel.getErrorLiveData().observe(viewLifecycleOwner, Observer {
            if(it) {
                binding.errorView.playAnimation()
            }
        })
    }

}
