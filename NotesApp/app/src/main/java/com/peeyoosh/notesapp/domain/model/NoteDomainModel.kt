package com.peeyoosh.notesapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteDomainModel(
    val id: Int?,
    var title: String,
    var description: String,
    var imageUrl: String?,
    var creationTime: Long,
    var isEdit: Boolean
) : Parcelable
