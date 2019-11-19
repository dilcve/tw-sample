package com.rf.tw_sample.data.repository

import com.rf.tw_sample.data.api.ApiService
import com.rf.tw_sample.data.response.mapToEntity
import com.rf.tw_sample.domain.repository.ProjectRepository
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    ProjectRepository {

    override fun loadProjects() =
        apiService.projects
            .map {
                it.projects.map { project ->
                    project.mapToEntity()
                }
            }
}

