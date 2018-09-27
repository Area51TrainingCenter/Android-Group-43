package pe.area51.notepad.data.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM notes")
    List<Note> getAll();

    @Query("SELECT * FROM notes WHERE id = :id")
    Note getById(long id);

    @Insert
    long insert(Note note);

    @Delete
    int delete(Note note);

    @Update
    int update(Note note);
}
