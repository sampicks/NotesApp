package com.peeyoosh.notesapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peeyoosh.notesapp.data.model.Note
import com.peeyoosh.notesapp.databinding.ActivityMainBinding
import com.peeyoosh.notesapp.domain.model.NoteDomainModel
import com.peeyoosh.notesapp.domain.repository.NoteRepository
import com.peeyoosh.notesapp.presentation.ui.NoteAddEditActivity
import com.peeyoosh.notesapp.presentation.ui.adapter.NoteAdapter
import com.peeyoosh.notesapp.presentation.util.Constant
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

    lateinit var mRecyclerView: RecyclerView
    lateinit var mNoteAdapter: NoteAdapter
    lateinit var mEmptyView : View
    @Inject
    lateinit var noteRepository: NoteRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        feedDataOnlyFirstTime()
        fetchList()

        binding.fab.setOnClickListener { view ->
            openNewEditActivity(null)
        }
    }

    private fun setupUI() {
        setSupportActionBar(binding.toolbar)
        getSupportActionBar()?.setTitle(getString(R.string.notes))

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        mRecyclerView = binding!!.recyclerView
        mEmptyView = binding!!.emptyView.emptyLayout

        val animation: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(
            this@MainActivity, resources.getIdentifier(
                "layout_animation_from_right", "anim",
                packageName
            )
        )
        mRecyclerView.setLayoutAnimation(animation)
        mNoteAdapter = NoteAdapter()
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this));
        mRecyclerView.adapter = mNoteAdapter

        mNoteAdapter.onItemClick = { noteDomainModel ->
            openNewEditActivity(noteDomainModel)
            // do something with your item
            Log.e(TAG, "selected item ======== " + noteDomainModel.id)
        }
    }

    private fun fetchList() {
        noteViewModel.fetchNotesList().observe(this, Observer {
            if(it!=null && it.size>0){
                mEmptyView.visibility=View.GONE
                mRecyclerView.visibility=View.VISIBLE
                mNoteAdapter.submitList(it)
                mNoteAdapter.notifyDataSetChanged()
                mRecyclerView.scheduleLayoutAnimation()

            }
            else{
                mEmptyView.visibility=View.VISIBLE
                mRecyclerView.visibility=View.GONE
            }

        })
    }

    private fun openNewEditActivity(noteDomainModel: NoteDomainModel?) {
        val intent = Intent(this, NoteAddEditActivity::class.java)
        if (noteDomainModel != null) {
            intent.putExtra(Constant.KEY_NOTE, noteDomainModel)
        }

        startForResult.launch(intent)
    }

    val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                fetchList()
            }
        }

    private fun feedDataOnlyFirstTime() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if (!prefs.getBoolean("firstTime", false)) {
            // run your one time code here
            GlobalScope.launch(Dispatchers.IO) {
                noteRepository.addNote(
                    Note(
                        id = null,
                        "The shortest article",
                        "The shortest article description",
                        "https://via.placeholder.com/100/03DAC5/000000/?Text=Test",
                        System.currentTimeMillis(),
                        false
                    )
                )
                noteRepository.addNote(
                    Note(
                        null,
                        "Value is too short",
                        "Value is large now with description",
                        "https://via.placeholder.com/100/6200EE/FFFFFF/?Text=Test",
                        System.currentTimeMillis(),
                        true
                    )
                )
                noteRepository.addNote(
                    Note(
                        null,
                        "Good Weather",
                        "Rain rain go away",
                        "https://via.placeholder.com/120/FFBB86FC/FFFFFF/?text=IPaddress.net",
                        System.currentTimeMillis(),
                        false
                    )
                )

            }

            val editor = prefs.edit()
            editor.putBoolean("firstTime", true)
            editor.apply()
        }
    }
}