package com.peeyoosh.notesapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.peeyoosh.notesapp.data.model.Note
import com.peeyoosh.notesapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val repository = (application as NoteApplication).noteRepository

        GlobalScope.launch(Dispatchers.IO) {
            repository.addNote(Note(1,"test","description","test url",System.currentTimeMillis(),false))
            repository.addNote(Note(2,"test 2","description","test url",System.currentTimeMillis(),false))
            repository.addNote(Note(3,"test 3","description","test url",System.currentTimeMillis(),false))

            repository.getNotes().forEach {
                Log.d(TAG,it.toString())
            }
        }

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}