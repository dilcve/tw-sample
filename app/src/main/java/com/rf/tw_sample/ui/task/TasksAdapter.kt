package com.rf.tw_sample.ui.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rf.tw_sample.R
import com.rf.tw_sample.databinding.ListItemTwoLinesWithImgBinding
import com.rf.tw_sample.domain.entity.Task

class TasksAdapter(private val onItemClickedListener: (Task) -> Unit) :
    RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    private var projectList: List<Task> = listOf()

    fun setData(tasks: List<Task>) {
        projectList = tasks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ListItemTwoLinesWithImgBinding.inflate(LayoutInflater.from(parent.context))
        return TaskViewHolder(binding, onItemClickedListener)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(projectList[position])
    }

    override fun getItemCount(): Int = projectList.size

    class TaskViewHolder(
        val binding: ListItemTwoLinesWithImgBinding,
        val listener: (Task) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var item: Task

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(task: Task) {
            item = task
            binding.name.text = task.name
            binding.description.text =
                binding.name.context.getString(R.string.created_by, task.author, task.description)
            Glide.with(binding.root.context)
                .load(task.creatorAvatarUrl)
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
