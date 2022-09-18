package com.peeyoosh.notesapp.domain.mapper

import com.peeyoosh.notesapp.data.model.Note
import com.peeyoosh.notesapp.domain.model.NoteDomainModel
import javax.inject.Inject

class NoteDomainMapper @Inject constructor() {
    fun transformToData(note: NoteDomainModel): Note {
        return Note(
            note.id,
            note.title,
            note.description,
            note.imageUrl,
            note.creationTime,
            note.isEdit
        )
    }
}