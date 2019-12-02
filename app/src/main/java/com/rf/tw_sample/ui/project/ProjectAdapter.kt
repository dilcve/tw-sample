package com.rf.tw_sample.ui.project

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rf.tw_sample.R
import com.rf.tw_sample.databinding.ListItemProjectBinding
import com.rf.tw_sample.domain.entity.Project

class ProjectAdapter(private val onItemClickedListener: (Project) -> Unit) :
    RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    private var projectList: List<Project> = listOf()

    fun setData(projects: List<Project>) {
        projectList = projects
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = ListItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectViewHolder(binding, onItemClickedListener)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projectList[position])
    }

    override fun getItemCount(): Int = projectList.size

    class ProjectViewHolder(
        val binding: ListItemProjectBinding,
        val listener: (Project) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var item: Project

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(project: Project) {
            item = project
            binding.name.text = project.name
            binding.name.compoundDrawableTintList =
                ColorStateList.valueOf(binding.root.context.getColor(if (project.starred) R.color.starred else R.color.not_starred))
        }

        override fun onClick(v: View?) {
            listener(item)
        }
    }
}
