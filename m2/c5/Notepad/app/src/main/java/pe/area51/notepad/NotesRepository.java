package pe.area51.notepad;

import java.util.List;

public interface NotesRepository {

    List<Note> getNotes();

    Note getNoteById(String noteId);

    Note insertNote(Note note);

    boolean updateNote(Note note);

    boolean deleteNoteById(String noteId);

}
