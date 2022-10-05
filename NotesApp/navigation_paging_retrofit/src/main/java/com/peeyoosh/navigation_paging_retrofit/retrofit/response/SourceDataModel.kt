package com.peeyoosh.navigation_paging_retrofit.retrofit.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceDataModel(
    var id: String?,
    var name: String?
) : Parcelable