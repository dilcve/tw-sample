package com.rf.tw_sample.data

import com.rf.tw_sample.data.api.ApiService
import com.rf.tw_sample.data.repository.TaskRepositoryImpl
import com.rf.tw_sample.data.response.TaskResponse
import com.rf.tw_sample.data.response.TasksResponse
import com.rf.tw_sample.domain.repository.TaskRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TaskRepositoryImplTest {

    private lateinit var repository: TaskRepository

    @Mock
    lateinit var apiService: ApiService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = TaskRepositoryImpl(apiService)
    }

    @Test
    fun getTasks() {
        val testId = "testId"
        `when`(apiService.getTasksByProject(testId)).thenReturn(
            Single.just(
                TasksResponse(
                    "Ok",
                    listOf(TaskResponse())
                )

            )
        )

        repository.loadTasksByProject(testId)
            .test()
            .assertValueCount(1)
            .assertComplete()

    }
}