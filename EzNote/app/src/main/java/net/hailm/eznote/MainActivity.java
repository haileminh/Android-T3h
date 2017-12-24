package net.hailm.eznote;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.hailm.eznote.fragment.CreateNoteFragment;
import net.hailm.eznote.fragment.ListNoteFragment;
import net.hailm.eznote.model.Note;

public class MainActivity extends AppCompatActivity {
    private CreateNoteFragment createNoteFragment;
    private ListNoteFragment listNoteFragment;

    private Note note;

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents() {
        showListNoteFragment();
    }

    public void showListNoteFragment() {
        listNoteFragment = new ListNoteFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(android.R.id.content, listNoteFragment)
                .commit();
    }

    public void showCreateNote() {
        createNoteFragment = new CreateNoteFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(android.R.id.content, createNoteFragment)
                .commit();
    }
}
