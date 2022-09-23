package com.peeyoosh.navigation_paging_retrofit.retrofit.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleDataModel(
    var author: String?,
    var content: String?,
    var description: String?,
    var publishedAt: String?,
    var source: SourceDataModel?,
    var title: String?,
    var url: String?,
    var urlToImage: String?
) : Parcelable