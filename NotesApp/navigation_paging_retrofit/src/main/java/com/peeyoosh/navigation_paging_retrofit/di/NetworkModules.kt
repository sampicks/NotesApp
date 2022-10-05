package com.peeyoosh.navigation_paging_retrofit.di

import com.peeyoosh.navigation_paging_retrofit.repository.NewsRepository
import com.peeyoosh.navigation_paging_retrofit.retrofit.NewsInterface
import com.peeyoosh.navigation_paging_retrofit.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModules {

    @Singleton
    @Provides
    fun provideNetworkLogging(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return client
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsInterface(retrofit: Retrofit): NewsInterface {
        return retrofit.create(NewsInterface::class.java)
    }

    @Provides
    fun provideNewsRepository(newsInterface: NewsInterface): NewsRepository {
        return NewsRepository(newsInterface)
    }

}