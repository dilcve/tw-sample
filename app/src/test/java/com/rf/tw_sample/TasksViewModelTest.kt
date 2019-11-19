package com.rf.tw_sample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rf.tw_sample.domain.common.TestScheduleProvider
import com.rf.tw_sample.domain.entity.Task
import com.rf.tw_sample.domain.usecase.TaskUseCase
import com.rf.tw_sample.ui.task.TasksViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class TasksViewModelTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>
    @Mock
    private lateinit var tasksObserver: Observer<List<Task>>
    @Mock
    private lateinit var taskUseCase: TaskUseCase

    private lateinit var viewModel: TasksViewModel
    private val schedulerProvider = TestScheduleProvider()

    private val testId = "testId"

    private val mockTaskList = listOf(
        Task(
            "Task 1",
            "it's a task",
            "John Doe",
            "https://lh3.googleusercontent.com/Xn1X_pIDKMxzvIyqCDT6l0_G8-3IAHkQe-LhD4OTYAF7qBlRUmBcpiBkWQIswyDtLqM=s360-rw"
        ), Task(
            "Task 2",
            "it's a task",
            "John Doe",
            "https://lh3.googleusercontent.com/Xn1X_pIDKMxzvIyqCDT6l0_G8-3IAHkQe-LhD4OTYAF7qBlRUmBcpiBkWQIswyDtLqM=s360-rw"
        ), Task(
            "Task 3",
            "it's a task",
            "John Doe",
            "https://lh3.googleusercontent.com/Xn1X_pIDKMxzvIyqCDT6l0_G8-3IAHkQe-LhD4OTYAF7qBlRUmBcpiBkWQIswyDtLqM=s360-rw"
        )
    )

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = TasksViewModel(taskUseCase, schedulerProvider)
        viewModel.getLoadingLiveData().observeForever(loadingObserver)
        viewModel.getTasksLiveData().observeForever(tasksObserver)
    }

    @Test
    fun loadTasksByProject() {
        `when`(taskUseCase.loadTasksByProject(testId))
            .thenReturn(
                Single.just(mockTaskList)
            )

        viewModel.loadTasksByProject(testId)

        verify(loadingObserver).onChanged(true)
        verify(loadingObserver).onChanged(false)
        verify(tasksObserver).onChanged(mockTaskList)
    }

}