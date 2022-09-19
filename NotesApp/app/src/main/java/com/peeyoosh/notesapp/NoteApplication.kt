package com.peeyoosh.notesapp

import android.app.Application
import com.peeyoosh.notesapp.domain.repository.NoteRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class NoteApplication : Application() {

    @Inject
    lateinit var noteRepository: NoteRepository

}