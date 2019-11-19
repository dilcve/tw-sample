package com.rf.tw_sample.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rf.tw_sample.domain.common.SchedulerProvider
import com.rf.tw_sample.domain.entity.Task
import com.rf.tw_sample.domain.usecase.TaskUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TasksViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val tasks = MutableLiveData<List<Task>>()

    private val compositeDisposable = CompositeDisposable()

    private val loading = MutableLiveData<Boolean>()

    fun getTasksLiveData(): LiveData<List<Task>> = tasks

    fun getLoadingLiveData(): LiveData<Boolean> = loading

    fun loadTasksByProject(projectId: String) {
        loading.value = true
        compositeDisposable.add(
            taskUseCase.loadTasksByProject(projectId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    tasks.value = it
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
