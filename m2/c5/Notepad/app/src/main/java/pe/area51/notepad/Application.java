package pe.area51.notepad;

import android.arch.persistence.room.Room;

import io.requery.android.database.sqlite.RequerySQLiteOpenHelperFactory;
import pe.area51.notepad.data.room.NotesRoomDatabase;
import pe.area51.notepad.data.room.NotesRoomRepository;
import pe.area51.notepad.data.sqlite.NotesSqLiteDatabase;
import pe.area51.notepad.data.sqlite.NotesSqLiteRepository;

public class Application extends android.app.Application {

    private NotesRepository notesRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        /*final NotesSqLiteDatabase notesSqLiteDatabase = new NotesSqLiteDatabase(this, "notesSqLiteDatabase");
        notesRepository = new NotesSqLiteRepository(notesSqLiteDatabase);*/

        final NotesRoomDatabase notesRoomDatabase = Room
                .databaseBuilder(this, NotesRoomDatabase.class, "notesRoomDatabase")
                .openHelperFactory(new RequerySQLiteOpenHelperFactory())
                .build();
        notesRepository = new NotesRoomRepository(notesRoomDatabase);
    }

    public NotesRepository getNotesRepository() {
        return notesRepository;
    }
}
