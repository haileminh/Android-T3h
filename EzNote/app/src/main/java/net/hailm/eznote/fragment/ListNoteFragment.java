package net.hailm.eznote.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.hailm.eznote.MainActivity;
import net.hailm.eznote.R;
import net.hailm.eznote.adapter.ListNoteAdapter;
import net.hailm.eznote.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hai_l on 07/12/2017.
 */

public class ListNoteFragment extends Fragment implements View.OnClickListener {
    private FloatingActionButton fabCreate;
    private View rootView;
    private RecyclerView rcvListNotes;
    private List<Note> notes;
    private ListNoteAdapter listNoteAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list_note, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponents();
    }

    private void initializeComponents() {
        fabCreate = rootView.findViewById(R.id.fab_create);
        notes = new ArrayList<>();

        notes.add(new Note("07/12/2017", "Đi học..................", Note.BG_CAM    ));
        notes.add(new Note("08/12/2017", "Ngày mai nắng lên.", ""));

        if (((MainActivity) getActivity()).getNote() != null) {
            Note note = ((MainActivity) getActivity()).getNote();
            notes.add(note);
        }


        rcvListNotes = rootView.findViewById(R.id.rcv_list_notes);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rcvListNotes.setLayoutManager(llm);

        listNoteAdapter = new ListNoteAdapter(notes, getContext());
        rcvListNotes.setAdapter(listNoteAdapter);

        fabCreate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_create:
                showCreateNote();
                break;

            default:
                break;
        }
    }

    private void showCreateNote() {
        ((MainActivity) getActivity()).showCreateNote();
    }
}
