package com.peeyoosh.notesapp.data.repository

import com.peeyoosh.notesapp.data.db.NoteDao
import com.peeyoosh.notesapp.data.model.Note
import com.peeyoosh.notesapp.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(val noteDao: NoteDao) : NoteRepository {

    override suspend fun getNotes() = noteDao.getNotes()

    override suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}