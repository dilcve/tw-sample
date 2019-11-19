package com.rf.tw_sample.data.repository

import com.rf.tw_sample.data.api.ApiService
import com.rf.tw_sample.data.response.mapToEntity
import com.rf.tw_sample.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val apiService: ApiService) : TaskRepository {

    override fun loadTasksByProject(id: String) =
        apiService.getTasksByProject(id)
            .map {
                it.tasks.map { task ->
                    task.mapToEntity()
                }
            }
}
