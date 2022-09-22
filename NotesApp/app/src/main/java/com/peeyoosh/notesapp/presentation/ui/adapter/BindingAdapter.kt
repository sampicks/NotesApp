package com.peeyoosh.notesapp.presentation.ui.adapter

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.peeyoosh.notesapp.R
import com.peeyoosh.notesapp.presentation.util.Constant.convertLongToTime
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

// databinding : make visibility of view depends upon boolean value.
@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.setVisibility(if (value) View.VISIBLE else View.GONE)
}

@SuppressLint("SetTextI18n")
@BindingAdapter("dateFormattedText")
fun TextView.dateFormattedText(longValue: Long) {
    this.text = this.context.getString(R.string.created) + convertLongToTime(longValue)
}