package com.rf.tw_sample.di

import com.rf.tw_sample.domain.common.SchedulerProvider
import com.rf.tw_sample.util.AppSchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class SchedulerModule {

    @Reusable
    @Provides
    fun provideSchedulerProvider() : SchedulerProvider = AppSchedulerProvider()
}