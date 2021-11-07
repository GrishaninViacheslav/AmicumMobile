package com.e.amicummobile.repository.remoteRepository

import com.e.amicummobile.config.Bootstrap
import com.e.amicummobile.modelAmicum.*
import com.e.amicummobile.repository.IRepositoryRemote
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.e.amicummobile.repository.remoteRepository.api.ApiService
import com.e.amicummobile.repository.remoteRepository.api.BaseInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImpl : IRepositoryRemote {
    override suspend fun getData(configToRequest: ConfigToRequest): String {
        return getService(BaseInterceptor.interceptor).getNotificationAsync(configToRequest).await()              // Добавляем suspend и .await()
    }

    private fun getService(interceptor: Interceptor): ApiService {
        return createRetrofit(interceptor).create(ApiService::class.java)
    }

    private fun createRetrofit(interceptor: Interceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Bootstrap.DEFAULT_URL_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(createOkHttpClient(interceptor))
            .build()
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

}