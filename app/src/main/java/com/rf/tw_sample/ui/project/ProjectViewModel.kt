package com.rf.tw_sample.ui.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rf.tw_sample.domain.common.SchedulerProvider
import com.rf.tw_sample.domain.entity.Project
import com.rf.tw_sample.domain.usecase.ProjectUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ProjectViewModel @Inject constructor(
    private val projectUseCase: ProjectUseCase,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val projects = MutableLiveData<List<Project>>()

    private val compositeDisposable = CompositeDisposable()

    private val loading = MutableLiveData<Boolean>()

    init {
        loadProjects()
    }

    fun getProjectsLiveData(): LiveData<List<Project>> = projects

    fun getLoadingLiveData(): LiveData<Boolean> = loading

    private fun loadProjects() {
        loading.value = true
        compositeDisposable.add(
            projectUseCase.loadProjects()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    projects.value = it
                    loading.value = false
                }, {
                    //handle some error
                    loading.value = false
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
