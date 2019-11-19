package com.rf.tw_sample.ui.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rf.tw_sample.databinding.ListItemTwoLinesWithImgBinding
import com.rf.tw_sample.domain.entity.Project

class ProjectAdapter(private val onItemClickedListener: (Project) -> Unit) :
    RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    private var projectList: List<Project> = listOf()

    fun setData(projects: List<Project>) {
        projectList = projects
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = ListItemTwoLinesWithImgBinding.inflate(LayoutInflater.from(parent.context))
        return ProjectViewHolder(binding, onItemClickedListener)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projectList[position])
    }

    override fun getItemCount(): Int = projectList.size

    class ProjectViewHolder(
        val binding: ListItemTwoLinesWithImgBinding,
        val listener: (Project) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var item: Project

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(project: Project) {
            item = project
            binding.name.text = project.name
            binding.description.text = project.description
            Glide.with(binding.root.context)
                .load(project.logo)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_delete)
                .dontAnimate()
                .into(binding.img)

        }

        override fun onClick(v: View?) {
            listener(item)
        }
    }
}
