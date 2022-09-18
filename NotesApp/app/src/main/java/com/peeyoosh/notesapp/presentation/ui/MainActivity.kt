package com.peeyoosh.notesapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.peeyoosh.notesapp.data.model.Note
import com.peeyoosh.notesapp.databinding.ActivityMainBinding
import com.peeyoosh.notesapp.domain.repository.NoteRepository
import com.peeyoosh.notesapp.presentation.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        noteViewModel=ViewModelProvider(this).get(NoteViewModel::class.java)

//        GlobalScope.launch(Dispatchers.IO) {
//            noteRepository.addNote(Note(id=null,"test","description","test url",System.currentTimeMillis(),false))
//            noteRepository.addNote(Note(null,"test 2","description","test url",System.currentTimeMillis(),false))
//            noteRepository.addNote(Note(null,"test 3","description","test url",System.currentTimeMillis(),false))
//
//            noteRepository.getNotes().forEach {
//                Log.d(TAG,it.toString())
//            }
//        }

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}