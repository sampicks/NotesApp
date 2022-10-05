package com.peeyoosh.notesapp.presentation.util

import java.text.SimpleDateFormat
import java.util.*

object Constant {
    const val KEY_NOTE = "KEY_NOTE"
    const val DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm"

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(DATE_TIME_FORMAT)
        return format.format(date)
    }
}