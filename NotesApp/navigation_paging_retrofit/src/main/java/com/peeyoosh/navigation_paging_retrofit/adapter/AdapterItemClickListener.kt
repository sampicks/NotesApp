package com.peeyoosh.navigation_paging_retrofit.adapter

import com.peeyoosh.navigation_paging_retrofit.retrofit.response.ArticleDataModel

interface AdapterItemClickListener {

    fun clickListener(article: ArticleDataModel)

}