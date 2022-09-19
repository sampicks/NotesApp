package com.peeyoosh.notesapp.presentation.ui.adapter

import android.text.TextUtils
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.peeyoosh.notesapp.R
import com.squareup.picasso.Picasso

// databinding : url to view extension function
@BindingAdapter("imageUrl")
fun ImageView.imageFromUrl(url: String?) {
    if (!TextUtils.isEmpty(url) && url!!.trim().length > 0) {
        Picasso.get().load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(this)
    } else {
        this.setImageDrawable(
            ContextCompat.getDrawable(
                this.context,
                R.drawable.ic_launcher_background
            )
        )
    }
}