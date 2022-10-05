package com.peeyoosh.navigation_paging_retrofit.retrofit

import com.peeyoosh.navigation_paging_retrofit.retrofit.response.NewsResponseDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {

    @GET("top-headlines")
    suspend fun getAllNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): NewsResponseDataModel
}