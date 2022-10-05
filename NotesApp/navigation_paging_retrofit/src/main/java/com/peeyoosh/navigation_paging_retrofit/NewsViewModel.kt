package com.peeyoosh.navigation_paging_retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.peeyoosh.navigation_paging_retrofit.repository.NewsRepository
import com.peeyoosh.navigation_paging_retrofit.retrofit.response.ArticleDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(newsRepository: NewsRepository) : ViewModel() {

    val listData: LiveData<PagingData<ArticleDataModel>> = newsRepository.getAllNewsStream().cachedIn(viewModelScope)
}