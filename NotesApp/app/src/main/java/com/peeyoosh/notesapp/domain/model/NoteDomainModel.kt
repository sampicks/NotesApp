package com.peeyoosh.notesapp.domain.model

data class NoteDomainModel(
    val id: Int?,
    val title: String,
    val description: String,
    val imageUrl: String?,
    val creationTime: Long,
    val isEdit: Boolean
)
