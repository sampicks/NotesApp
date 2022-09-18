package com.peeyoosh.notesapp.domain.mapper

import com.peeyoosh.notesapp.data.model.Note
import com.peeyoosh.notesapp.domain.model.NoteDomainModel
import javax.inject.Inject

class NoteDataMapper  @Inject constructor() {
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

    fun transformToList(noteList : List<Note>) : List<NoteDomainModel>{
        val noteDomainList = mutableListOf<NoteDomainModel>()
        noteList.forEach({
            val noteDomainModel = transformToDomain(it)
            noteDomainList.add(noteDomainModel)
        }
        )
        return noteDomainList
    }
}