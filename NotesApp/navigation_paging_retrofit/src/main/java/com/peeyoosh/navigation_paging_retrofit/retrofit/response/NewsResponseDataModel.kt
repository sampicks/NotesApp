package com.peeyoosh.navigation_paging_retrofit.retrofit.response

data class NewsResponseDataModel(
    var articles: List<ArticleDataModel>?,
    var status: String?,
    var totalResults: Int?
)