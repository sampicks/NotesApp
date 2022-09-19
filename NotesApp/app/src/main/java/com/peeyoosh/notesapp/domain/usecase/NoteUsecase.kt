package com.peeyoosh.notesapp.domain.usecase

import com.peeyoosh.notesapp.domain.mapper.NoteDataMapper
import com.peeyoosh.notesapp.domain.mapper.NoteDomainMapper
import com.peeyoosh.notesapp.domain.model.NoteDomainModel
import com.peeyoosh.notesapp.domain.repository.NoteRepository
import javax.inject.Inject

class NoteUsecase @Inject constructor(
    val noteRepository: NoteRepository,
    val noteDataMapper: NoteDataMapper, val noteDomainMapper: NoteDomainMapper
) {

    suspend fun getNotesData(): List<NoteDomainModel> {
        val noteList = noteRepository.getNotes()
        return noteDataMapper.transformToList(noteList)
    }

    suspend fun addNote(note: NoteDomainModel) {
        noteRepository.addNote(noteDomainMapper.transformToData(note))
    }

    suspend fun updateNote(note: NoteDomainModel) {
        noteRepository.updateNote(noteDomainMapper.transformToData(note))
    }

    suspend fun deleteNote(note: NoteDomainModel) {
        noteRepository.deleteNote(noteDomainMapper.transformToData(note))
    }
}