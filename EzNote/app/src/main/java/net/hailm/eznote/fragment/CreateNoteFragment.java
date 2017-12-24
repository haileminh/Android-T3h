package net.hailm.eznote.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import net.hailm.eznote.MainActivity;
import net.hailm.eznote.R;
import net.hailm.eznote.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hai_l on 07/12/2017.
 */

public class CreateNoteFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private EditText edtContent;
    private DatePicker datePicker;
    private String date;
    private Button btnCreate;
    private Button btnXanh, btnDo, btnCam, btnTim;
    private String color;
    private List<Note> notes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_create_note, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponents();
    }

    private void initializeComponents() {
        color = "";
        notes = new ArrayList<>();
        edtContent = rootView.findViewById(R.id.edt_content);
        btnCreate = rootView.findViewById(R.id.btn_create);
        datePicker = rootView.findViewById(R.id.date);

        btnXanh = rootView.findViewById(R.id.btn_xanh);
        btnTim = rootView.findViewById(R.id.btn_tim);
        btnCam = rootView.findViewById(R.id.btn_cam);
        btnDo = rootView.findViewById(R.id.btn_do);

        btnCreate.setOnClickListener(this);
        btnDo.setOnClickListener(this);
        btnXanh.setOnClickListener(this);
        btnCam.setOnClickListener(this);
        btnTim.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_xanh:
                color = Note.BG_XANH;
                edtContent.setBackgroundColor(Color.parseColor("#029acc"));
//                edtContent.setBackgroundResource(R.drawable.bg_xanh);
                break;
            case R.id.btn_cam:
                color = Note.BG_CAM;
                edtContent.setBackgroundColor(Color.parseColor("#ff8902"));
//                edtContent.setBackgroundResource(R.drawable.bg_cam);
                break;
            case R.id.btn_do:
                color = Note.BG_DO;
                edtContent.setBackgroundColor(Color.parseColor("#FF7043"));
//                edtContent.setBackgroundResource(R.drawable.bg_do);
                break;
            case R.id.btn_tim:
                color = Note.BG_TIM;
                edtContent.setBackgroundColor(Color.parseColor("#aa67cc"));
//                edtContent.setBackgroundResource(R.drawable.bg_tim);
                break;
            case R.id.btn_create:
                createNote();
                break;
            default:
                break;
        }
    }

    private void createNote() {
        String content = edtContent.getText().toString();
        int month = datePicker.getMonth() + 1;
        int dayOfMonth = datePicker.getDayOfMonth();
        int year = datePicker.getYear();

        int backgroundColor = edtContent.getDrawingCacheBackgroundColor();
        date = String.valueOf(dayOfMonth + "/" + month + "/" + year);

        if (content.isEmpty() || date.isEmpty()) {
            Toast.makeText(getContext(), "Please enter values...", Toast.LENGTH_SHORT).show();
            return;
        }

//        notes.add(new Note(date, content, ""));
        Note note = new Note(date, content, color);
        ((MainActivity) getActivity()).setNote(note);

        ((MainActivity) getActivity()).showListNoteFragment();

    }

}
