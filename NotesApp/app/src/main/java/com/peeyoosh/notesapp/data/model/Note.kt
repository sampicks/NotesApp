package com.peeyoosh.notesapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id : Int?=null,
    val title : String,
    val description : String,
    val imageUrl : String?,
    val creationTime : Long,
    val isEdit : Boolean
)
