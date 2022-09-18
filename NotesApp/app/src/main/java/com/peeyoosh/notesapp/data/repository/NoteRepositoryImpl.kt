package com.peeyoosh.notesapp.data.repository

import com.peeyoosh.notesapp.data.db.NoteDatabase
import com.peeyoosh.notesapp.data.model.Note
import com.peeyoosh.notesapp.domain.repository.NoteRepository

class NoteRepositoryImpl(private val noteDatabase: NoteDatabase) : NoteRepository {

    override suspend fun getNotes() = noteDatabase.noteDao().getNotes()

    override suspend fun addNote(note: Note) {
        noteDatabase.noteDao().addNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDatabase.noteDao().updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDatabase.noteDao().deleteNote(note)
    }
}