package  com.peeyoosh.notesapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.peeyoosh.notesapp.data.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}