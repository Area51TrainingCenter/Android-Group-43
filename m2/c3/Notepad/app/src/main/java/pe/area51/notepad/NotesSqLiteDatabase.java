package pe.area51.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class NotesSqLiteDatabase extends SQLiteOpenHelper {

    private static NotesSqLiteDatabase INSTANCE;

    public static NotesSqLiteDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = new NotesSqLiteDatabase(context.getApplicationContext());
        }
        return INSTANCE;
    }

    private final static int DATABASE_VERSION = 1;

    private final static String DATABASE_NAME = "notes.db";

    private NotesSqLiteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sql = "CREATE TABLE notes (_id INTEGER PRIMARY KEY, title TEXT, content TEXT, creationTimestamp INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new UnsupportedOperationException("This shouldn't be executed, because we are in the first version.");
    }

    public List<Note> getNotes() {
        final Cursor queryCursor = getReadableDatabase().rawQuery("SELECT * FROM notes", null);
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

    public Note getNoteByTitle(String title) {
        return null;
    }

    public Note insertNote(Note note) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put("title", note.getTitle());
        contentValues.put("content", note.getContent());
        contentValues.put("creationTimestamp", note.getCreationTimestamp());
        final long noteId = getWritableDatabase().insert("notes", null, contentValues);
        return new Note(String.valueOf(noteId), note.getTitle(), note.getContent(), note.getCreationTimestamp());
    }

}
