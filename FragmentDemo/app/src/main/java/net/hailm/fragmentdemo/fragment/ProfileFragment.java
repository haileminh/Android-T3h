package net.hailm.fragmentdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.hailm.fragmentdemo.Key;
import net.hailm.fragmentdemo.MainActivity;
import net.hailm.fragmentdemo.R;

/**
 * Created by hai_l on 21/01/2018.
 */

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private TextView txtName, txtEmail;
    private Button btnEditProfile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initalizeComponents();

        Bundle bundle = getArguments();
        if (bundle != null) {
            txtName.setText(bundle.getString(Key.NAME));
            txtEmail.setText(bundle.getString(Key.EMAIL));
        } else {
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
        }

    }

    private void initalizeComponents() {
        txtName = rootView.findViewById(R.id.txt_name);
        txtEmail = rootView.findViewById(R.id.txt_email);
        btnEditProfile = rootView.findViewById(R.id.btn_edit_profile);


        btnEditProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit_profile:
                ((MainActivity) getActivity()).showEditorFragmentScreen();
                break;
            default:
                break;
        }
    }
}
