package com.rf.tw_sample.di

import android.app.Application
import com.rf.tw_sample.AppApplication
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        SchedulerModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        ApiServiceModule::class]
)
class AppModule {

    @Provides
    internal fun provideApplication(application: Application): AppApplication =
        application as AppApplication

    @Provides
    internal fun provideApplicationContext(appApplication: AppApplication) =
        appApplication.applicationContext
}