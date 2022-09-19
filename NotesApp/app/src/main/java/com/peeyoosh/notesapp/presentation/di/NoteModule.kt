package com.peeyoosh.notesapp.presentation.di

import android.content.Context
import androidx.room.Room
import com.peeyoosh.notesapp.data.db.NoteDao
import com.peeyoosh.notesapp.data.db.NoteDatabase
import com.peeyoosh.notesapp.data.repository.NoteRepositoryImpl
import com.peeyoosh.notesapp.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NoteModule {

    @Provides
    fun provideNoteDao(appDatabase: NoteDatabase): NoteDao {
        return appDatabase.noteDao()
    }

    @Singleton
    @Provides
    fun getRepositoryImpl(@ApplicationContext applicationContext : Context) : NoteRepository{
        return NoteRepositoryImpl(provideNoteDao(provideAppDatabase(applicationContext)))
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): NoteDatabase {
        return Room.databaseBuilder(
            appContext,
            NoteDatabase::class.java,
            "noteDB"
        )
            .build()
    }
}