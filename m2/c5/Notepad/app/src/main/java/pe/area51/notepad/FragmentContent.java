package pe.area51.notepad;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Date;

public class FragmentContent extends Fragment {

    private static final String KEY_ARG_NOTE_ID = "noteId";

    private EditText editTextTitle;
    private EditText editTextContent;

    private FragmentInteractionInterface fragmentInteractionInterface;
    private NotesRepository notesRepository;

    private String noteId;

    private Note note;

    public static FragmentContent newInstance(final String noteId) {
        final FragmentContent contentFragment = new FragmentContent();
        final Bundle arguments = new Bundle();
        arguments.putString(KEY_ARG_NOTE_ID, noteId);
        contentFragment.setArguments(arguments);
        return contentFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentInteractionInterface = (FragmentInteractionInterface) context;
        notesRepository = ((Application) context.getApplicationContext()).getNotesRepository();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle arguments = getArguments();
        checkArguments(arguments);
        noteId = arguments.getString(KEY_ARG_NOTE_ID);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_content, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.actionDeleteNote) {
            deleteNote();
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_content, container, false);
        editTextTitle = view.findViewById(R.id.editTextNoteTitle);
        editTextContent = view.findViewById(R.id.editTextNoteContent);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showNote();
    }

    @Override
    public void onStop() {
        super.onStop();
        updateNote();
    }

    private static void checkArguments(final Bundle arguments) {
        if (arguments != null && arguments.containsKey(KEY_ARG_NOTE_ID)) {
            return;
        }
        throw new RuntimeException("Fragment doesn't have needed arguments. Call newInstance() static creation method.");
    }

    private void showNote() {
        new AsyncTask<Void, Void, Note>() {
            @Override
            protected Note doInBackground(Void... voids) {
                return notesRepository.getNoteById(noteId);
            }

            @Override
            protected void onPostExecute(Note note) {
                final Date noteDate = new Date(note.getCreationTimestamp());
                final DateFormat dateFormat = DateFormat.getInstance();
                fragmentInteractionInterface.setToolbarTitle(dateFormat.format(noteDate));
                editTextTitle.setText(note.getTitle());
                editTextContent.setText(note.getContent());
                FragmentContent.this.note = note;
            }
        }.execute();
    }

    private void updateNote() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                notesRepository.updateNote(new Note(
                        noteId,
                        editTextTitle.getText().toString(),
                        editTextContent.getText().toString(),
                        note.getCreationTimestamp()
                ));
                return null;
            }
        }.execute();
    }

    private void deleteNote() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                notesRepository.deleteNoteById(noteId);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                fragmentInteractionInterface.finishFragment();
            }
        }.execute();
    }
}
