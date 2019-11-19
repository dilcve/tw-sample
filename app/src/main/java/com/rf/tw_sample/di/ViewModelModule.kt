package com.rf.tw_sample.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rf.tw_sample.ui.project.ProjectViewModel
import com.rf.tw_sample.ui.task.TasksViewModel
import com.rf.tw_sample.util.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ProjectViewModel::class)
    abstract fun bindProjectViewModel(projectViewModel: ProjectViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TasksViewModel::class)
    abstract fun bindTasksViewModel(tasksViewModel: TasksViewModel): ViewModel
}