package com.rf.tw_sample.ui.task

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rf.tw_sample.R
import com.rf.tw_sample.databinding.ListItemTaskBinding
import com.rf.tw_sample.domain.entity.Priority
import com.rf.tw_sample.domain.entity.Task
import com.rf.tw_sample.util.toSimpleDateFormat

class TasksAdapter(private val onItemClickedListener: (Task) -> Unit) :
    RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    private var projectList: List<Task> = listOf()

    fun setData(tasks: List<Task>) {
        projectList = tasks
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding =
            ListItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding, onItemClickedListener)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(projectList[position])
    }

    override fun getItemCount(): Int = projectList.size

    class TaskViewHolder(
        val binding: ListItemTaskBinding,
        val listener: (Task) -> Unit
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var item: Task

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(task: Task) {
            item = task
            binding.name.text = task.name
            binding.user.text = task.responsibleParty
            binding.date.text = task.createdOn.toSimpleDateFormat()

            binding.tag.apply {
                if (task.tags.isNotEmpty()) {
                    visibility = View.VISIBLE
                    val tag = task.tags.first()
                    text = tag.name
                    backgroundTintList = ColorStateList.valueOf(Color.parseColor(tag.color))
                } else {
                    visibility = View.GONE
                }
            }

            binding.priority.visibility = View.VISIBLE
            when (task.priority) {
                Priority.Low -> binding.priority.setImageResource(R.color.priority_low)
                Priority.Medium -> binding.priority.setImageResource(R.color.priority_medium)
                Priority.High -> binding.priority.setImageResource(R.color.priority_high)
                Priority.None -> binding.priority.visibility = View.GONE
            }
        }

        override fun onClick(v: View?) {
            listener(item)
        }
    }
}
