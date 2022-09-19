package com.peeyoosh.notesapp.domain.repository

import com.peeyoosh.notesapp.data.model.Note

interface NoteRepository {

    suspend fun getNotes() : List<Note>
    suspend fun addNote(note : Note)
    suspend fun updateNote(note : Note)
    suspend fun deleteNote(note : Note)
}