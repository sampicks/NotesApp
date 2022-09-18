package com.peeyoosh.notesapp

import android.app.Application
import com.peeyoosh.notesapp.data.db.NoteDatabase
import com.peeyoosh.notesapp.data.repository.NoteRepositoryImpl
import com.peeyoosh.notesapp.domain.repository.NoteRepository

class NoteApplication : Application() {

    lateinit var noteRepository: NoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val database = NoteDatabase.getDatabase(applicationContext)
        noteRepository = NoteRepositoryImpl(database)
    }
}