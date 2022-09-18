package com.peeyoosh.notesapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id : Int?=null,
    var title : String,
    var description : String,
    var imageUrl : String?,
    var creationTime : Long,
    var isEdit : Boolean
)
