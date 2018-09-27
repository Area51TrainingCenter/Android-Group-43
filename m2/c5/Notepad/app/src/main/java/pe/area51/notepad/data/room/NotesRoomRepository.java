package pe.area51.notepad.data.room;

import java.util.ArrayList;
import java.util.List;

import pe.area51.notepad.Note;
import pe.area51.notepad.NotesRepository;

public class NotesRoomRepository implements NotesRepository {

    private final NotesRoomDatabase notesRoomDatabase;

    public NotesRoomRepository(NotesRoomDatabase notesRoomDatabase) {
        this.notesRoomDatabase = notesRoomDatabase;
    }

    @Override
    public List<Note> getNotes() {
        final List<pe.area51.notepad.data.room.Note> roomNotes = notesRoomDatabase.getNotesDao().getAll();
        final List<Note> notes = new ArrayList<>();
        for (final pe.area51.notepad.data.room.Note roomNote : roomNotes) {
            notes.add(mapRoomNoteToNote(roomNote));
        }
        return notes;
    }

    @Override
    public Note getNoteById(String noteId) {
        final pe.area51.notepad.data.room.Note roomNote = notesRoomDatabase
                .getNotesDao()
                .getById(Long.valueOf(noteId));
        return mapRoomNoteToNote(roomNote);
    }

    @Override
    public Note insertNote(Note note) {
        final long id = notesRoomDatabase.getNotesDao().insert(
                new pe.area51.notepad.data.room.Note(
                        0,
                        note.getTitle(),
                        note.getContent(),
                        note.getCreationTimestamp()
                )
        );
        return new Note(String.valueOf(id),
                note.getTitle(),
                note.getContent(),
                note.getCreationTimestamp());
    }

    @Override
    public boolean updateNote(Note note) {
        return notesRoomDatabase
                .getNotesDao()
                .update(mapNoteToRoomNote(note)) > 0;
    }

    @Override
    public boolean deleteNoteById(String noteId) {
        return notesRoomDatabase
                .getNotesDao()
                .delete(new pe.area51.notepad.data.room.Note(
                        Long.valueOf(noteId),
                        "",
                        "",
                        0
                )) > 0;
    }

    private pe.area51.notepad.data.room.Note mapNoteToRoomNote(final Note note) {
        return new pe.area51.notepad.data.room.Note(
                Long.valueOf(note.getId()),
                note.getTitle(),
                note.getContent(),
                note.getCreationTimestamp()
        );
    }

    private Note mapRoomNoteToNote(final pe.area51.notepad.data.room.Note roomNote) {
        return new Note(
                String.valueOf(roomNote.getId()),
                roomNote.getTitle(),
                roomNote.getContent(),
                roomNote.getCreationTimestamp()
        );
    }
}
