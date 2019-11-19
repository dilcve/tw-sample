package com.rf.tw_sample.di

import com.rf.tw_sample.ui.project.ProjectFragment
import com.rf.tw_sample.ui.task.TasksFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeProjectFragment(): ProjectFragment

    @ContributesAndroidInjector
    abstract fun contributeTasksFragment(): TasksFragment
}