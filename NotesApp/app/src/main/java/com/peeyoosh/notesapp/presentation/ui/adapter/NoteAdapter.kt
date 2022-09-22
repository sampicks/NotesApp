package com.peeyoosh.notesapp.presentation.ui.adapter

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.peeyoosh.notesapp.BR
import com.peeyoosh.notesapp.R
import com.peeyoosh.notesapp.databinding.RecyclerNoteItemBinding
import com.peeyoosh.notesapp.domain.model.NoteDomainModel
import com.peeyoosh.notesapp.presentation.util.Constant
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var items: List<NoteDomainModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = RecyclerNoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.noteDomainModel,items.get(position))
        holder.bind(items.get(position))
    }

    inner class NoteViewHolder constructor(val viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root)
    {
//        val noteImageView = binding.imgNote
//        val noteTitleTxt = binding.txvTitle
//        val noteDescTxt = binding.txvDescription
//        val noteCreatedTxt = binding.txvDateCreated
//        val noteImageViewEdit = binding.imgEdit

        fun bind(note: NoteDomainModel) {
        // Comment following code because I have used Data Binding in xml hence no need to code.

            /*   // Check BindingAdapter class for extension method
               noteImageView.imageFromUrl(note.imageUrl)

               noteTitleTxt.setText(note.title)
               noteDescTxt.setText(note.description)

            noteCreatedTxt.setText(
                noteCreatedTxt.context.getString(R.string.created) + convertLongToTime(
                    note.creationTime
                )
            )
            if (note.isEdit) {
                noteImageViewEdit.visibility = View.VISIBLE
            } else {
                noteImageViewEdit.visibility = View.INVISIBLE
            }
*/
            itemView.setOnClickListener {
                onItemClick?.invoke(note)
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<NoteDomainModel>) {
        items = blogList
    }

    var onItemClick: ((NoteDomainModel) -> Unit)? = null
}