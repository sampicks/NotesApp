package com.peeyoosh.notesapp.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.peeyoosh.notesapp.MainActivity
import com.peeyoosh.notesapp.R
import com.peeyoosh.notesapp.databinding.ActivityNoteAddEditBinding
import com.peeyoosh.notesapp.domain.model.NoteDomainModel
import com.peeyoosh.notesapp.presentation.util.Constant
import com.peeyoosh.notesapp.presentation.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NoteAddEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteAddEditBinding

    lateinit var mEdtTitle: EditText
    lateinit var mEdtDesc: EditText
    lateinit var mEdtImageUrl: EditText

    lateinit var noteViewModel: NoteViewModel
    lateinit var noteDomainModel: NoteDomainModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteAddEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        setupUI()
    }

    private fun getIntentData() {
        if (intent.hasExtra(Constant.KEY_NOTE)) {
            noteDomainModel = intent.getParcelableExtra<NoteDomainModel>(Constant.KEY_NOTE)!!
        }
    }

    private fun setupUI() {
        setSupportActionBar(binding.toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        mEdtTitle = binding.editTextTitle
        mEdtDesc = binding.editTextDesc
        mEdtImageUrl = binding.editTextUrl

        if (::noteDomainModel.isInitialized) {
            mEdtTitle.setText(noteDomainModel.title)
            mEdtDesc.setText(noteDomainModel.description)
            mEdtImageUrl.setText(noteDomainModel.imageUrl)
            binding.buttonSave.visibility = View.GONE
            binding.layoutEditDelete.visibility = View.VISIBLE
            getSupportActionBar()?.setTitle(getString(R.string.edit_delete_note))
        }
        else{
            getSupportActionBar()?.setTitle(getString(R.string.add_note))
        }
        binding.buttonSave.setOnClickListener {
            if (isValidData()) {
                noteViewModel.saveNote(
                    NoteDomainModel(
                        null,
                        mEdtTitle.text.toString(),
                        mEdtDesc.text.toString(),
                        mEdtImageUrl.text.toString(),
                        System.currentTimeMillis(),
                        false
                    )
                )
                setResult(RESULT_OK)
                finish()
            }
            else {
                Toast.makeText(this,getString(R.string.validation_str),Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonEdit.setOnClickListener {
            if (isValidData()) {
                noteDomainModel.imageUrl = mEdtImageUrl.text.toString()
                noteDomainModel.title = mEdtTitle.text.toString()
                noteDomainModel.description = mEdtDesc.text.toString()
                noteDomainModel.isEdit = true
                noteViewModel.updateNote(noteDomainModel)
                setResult(RESULT_OK)
                finish()
            }
            else {
                Toast.makeText(this,getString(R.string.validation_str),Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonDelete.setOnClickListener {
                noteViewModel.deleteNote(noteDomainModel)
                setResult(RESULT_OK)
                finish()
        }
    }

    private fun isValidData(): Boolean {
        val title = mEdtTitle.text.toString()
        val desc = mEdtDesc.text.toString()
        return !TextUtils.isEmpty(title) && !TextUtils.isEmpty(desc) && title.trim().length>0 && desc.trim().length>0
    }
}