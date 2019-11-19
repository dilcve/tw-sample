package com.rf.tw_sample.di

import com.rf.tw_sample.data.BuildConfig
import com.rf.tw_sample.data.api.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        .addInterceptor(authInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor {
            var request = it.request()
            val headers = request.headers().newBuilder()
                .add("Authorization", Credentials.basic(BuildConfig.API_TOKEN, "x")).build()
            request = request.newBuilder().headers(headers).build()
            it.proceed(request)
        }
    }

    @Provides
    @Singleton
    @Named("apiUrl")
    fun provideApiUrl() = BuildConfig.API_ENDPOINT

    @Provides
    @Singleton
    fun provideApiService(
        okHttpClient: OkHttpClient,
        @Named("apiUrl") url: String
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}