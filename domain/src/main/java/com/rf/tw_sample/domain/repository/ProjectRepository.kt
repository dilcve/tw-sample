package com.rf.tw_sample.domain.repository

import com.rf.tw_sample.domain.entity.Project
import io.reactivex.Single

interface ProjectRepository {

    fun loadProjects(): Single<List<Project>>
}