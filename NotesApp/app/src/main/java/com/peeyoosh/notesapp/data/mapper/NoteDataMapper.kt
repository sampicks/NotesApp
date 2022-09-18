package com.peeyoosh.notesapp.domain.mapper

import com.peeyoosh.notesapp.data.model.Note
import com.peeyoosh.notesapp.domain.model.NoteDomainModel

class NoteDataMapper {
    fun transformToDomain(note: Note): NoteDomainModel {
        return NoteDomainModel(
            note.id,
            note.title,
            note.description,
            note.imageUrl,
            note.creationTime,
            note.isEdit
        )
    }
}