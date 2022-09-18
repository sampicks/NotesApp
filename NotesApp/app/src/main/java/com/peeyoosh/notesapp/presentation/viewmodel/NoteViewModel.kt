package com.peeyoosh.notesapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peeyoosh.notesapp.domain.model.NoteDomainModel
import com.peeyoosh.notesapp.domain.usecase.NoteUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "NoteViewModel"

@HiltViewModel
class NoteViewModel @Inject constructor() : ViewModel() {

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    @Inject
    lateinit var noteUsecase: NoteUsecase

    private val noteListLiveData = MutableLiveData<List<NoteDomainModel>>()

    fun fetchNotesList(): LiveData<List<NoteDomainModel>> {
        viewModelScope.launch(Dispatchers.IO + viewModelJob) {
            noteListLiveData.postValue(noteUsecase.getNotesData())
        }
        return noteListLiveData
    }

    fun saveNote(note: NoteDomainModel) {
        viewModelScope.launch(Dispatchers.IO + viewModelJob) {
            noteUsecase.addNote(note)
        }
    }

    fun updateNote(note: NoteDomainModel) {
        viewModelScope.launch(Dispatchers.IO + viewModelJob) {
            noteUsecase.updateNote(note)
        }
    }

    fun deleteNote(note: NoteDomainModel) {
        viewModelScope.launch(Dispatchers.IO + viewModelJob) {
            noteUsecase.deleteNote(note)
        }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}