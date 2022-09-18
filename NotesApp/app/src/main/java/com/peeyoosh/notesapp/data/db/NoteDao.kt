package  com.peeyoosh.notesapp.data.db

import androidx.room.*
import com.peeyoosh.notesapp.data.model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note : Note)

    @Update
    suspend fun updateNote(note : Note)

    @Delete
    suspend fun deleteNote(note : Note)

    @Query("SELECT * FROM note")
    suspend fun getNotes() : List<Note>
}