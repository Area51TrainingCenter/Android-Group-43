package pe.area51.notepad.data.sqlite;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import pe.area51.notepad.Note;
import pe.area51.notepad.NotesRepository;

public class NotesSqLiteRepository implements NotesRepository {

    private final NotesSqLiteDatabase notesSqLiteDatabase;

    public NotesSqLiteRepository(NotesSqLiteDatabase notesSqLiteDatabase) {
        this.notesSqLiteDatabase = notesSqLiteDatabase;
    }

    @Override
    public List<Note> getNotes() {
        final Cursor queryCursor = notesSqLiteDatabase.getReadableDatabase().rawQuery("SELECT * FROM notes", null);
        final List<Note> notes = new ArrayList<>();
        while (queryCursor.moveToNext()) {
            final long id = queryCursor.getLong(queryCursor.getColumnIndex("_id"));
            final String title = queryCursor.getString(queryCursor.getColumnIndex("title"));
            final String content = queryCursor.getString(queryCursor.getColumnIndex("content"));
            final long creationTimestamp = queryCursor.getLong(queryCursor.getColumnIndex("creationTimestamp"));
            final Note note = new Note(String.valueOf(id), title, content, creationTimestamp);
            notes.add(note);
        }
        queryCursor.close();
        return notes;
    }

    @Override
    public Note getNoteById(String noteId) {
        throw new RuntimeException("Not implemented!");
    }

    @Override
    public Note insertNote(Note note) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put("title", note.getTitle());
        contentValues.put("content", note.getContent());
        contentValues.put("creationTimestamp", note.getCreationTimestamp());
        final long id = notesSqLiteDatabase
                .getWritableDatabase()
                .insert("notes", null, contentValues);
        return new Note(String.valueOf(id),
                note.getTitle(),
                note.getContent(),
                note.getCreationTimestamp());
    }

    @Override
    public boolean updateNote(Note note) {
        throw new RuntimeException("Not implemented!");
    }

    @Override
    public boolean deleteNoteById(String noteId) {
        throw new RuntimeException("Not implemented!");
    }
}
