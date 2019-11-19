package com.rf.tw_sample.di

import com.rf.tw_sample.data.repository.ProjectRepositoryImpl
import com.rf.tw_sample.data.repository.TaskRepositoryImpl
import com.rf.tw_sample.domain.repository.ProjectRepository
import com.rf.tw_sample.domain.repository.TaskRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindProjectRepository(projectRepositoryImpl: ProjectRepositoryImpl): ProjectRepository

    @Binds
    abstract fun bindTaskRepository(taskRepositoryImpl: TaskRepositoryImpl): TaskRepository
}