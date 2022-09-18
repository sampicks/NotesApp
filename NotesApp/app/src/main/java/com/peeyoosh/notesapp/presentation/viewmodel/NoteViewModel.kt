package com.peeyoosh.notesapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        viewModelScope.launch(Dispatchers.IO + viewModelJob) {
            noteUsecase.getNotesData().forEach {
                Log.d(TAG, it.toString())
            }
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