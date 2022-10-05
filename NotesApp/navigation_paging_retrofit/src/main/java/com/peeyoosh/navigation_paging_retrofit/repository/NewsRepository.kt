package com.peeyoosh.navigation_paging_retrofit.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.peeyoosh.navigation_paging_retrofit.paging.NewsPagingSource
import com.peeyoosh.navigation_paging_retrofit.retrofit.NewsInterface
import com.peeyoosh.navigation_paging_retrofit.retrofit.response.ArticleDataModel

class NewsRepository(val newsInterface: NewsInterface) {


    fun getAllNewsStream(): LiveData<PagingData<ArticleDataModel>> = Pager(
        config = PagingConfig(
            20,
            5,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            NewsPagingSource(newsInterface)
        }
    ).liveData

}