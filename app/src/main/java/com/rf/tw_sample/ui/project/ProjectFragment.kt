package com.rf.tw_sample.ui.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rf.tw_sample.databinding.FragmentProjectBinding
import com.rf.tw_sample.di.Injectable
import com.rf.tw_sample.util.AppViewModelFactory
import javax.inject.Inject

class ProjectFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory

    private val viewModel by viewModels<ProjectViewModel> { viewModelFactory }

    private lateinit var binding: FragmentProjectBinding

    private lateinit var adapter: ProjectAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProjectBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.apply {
            this@apply.lifecycleOwner = viewLifecycleOwner
            this@apply.viewModel = this@ProjectFragment.viewModel
        }
        binding.viewModel

        adapter = ProjectAdapter {
            findNavController().navigate(
                ProjectFragmentDirections.actionProjectFragmentToTasksFragment(
                    it.id
                )
            )
        }

        binding.list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@ProjectFragment.adapter
        }

        binding.errorView.setOnClickListener { viewModel.loadProjects() }
        binding.errorMessage.setOnClickListener { viewModel.loadProjects() }

        subscribe()
    }

    private fun subscribe() {
        viewModel.getProjectsLiveData().observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        viewModel.getErrorLiveData().observe(viewLifecycleOwner, Observer {
            if(it) {
                binding.errorView.playAnimation()
            }
        })
    }

}
