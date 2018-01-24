package net.hailm.fragmentdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.hailm.fragmentdemo.Key;
import net.hailm.fragmentdemo.MainActivity;
import net.hailm.fragmentdemo.R;

/**
 * Created by hai_l on 21/01/2018.
 */

public class EditorFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private EditText edtName, edtEmail;
    private Button btnSubmit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragmnet_editor, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponents();
    }

    private void initializeComponents() {
        edtName = rootView.findViewById(R.id.edt_name);
        edtEmail = rootView.findViewById(R.id.edt_email);
        btnSubmit = rootView.findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                updateProfile();
                break;
        }
    }

    private void updateProfile() {
        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter data", Toast.LENGTH_SHORT).show();
            return;
        }
        ProfileFragment profileFrament = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Key.NAME, name);
        bundle.putString(Key.EMAIL, email);
        profileFrament.setArguments(bundle);

        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction()
                .replace(android.R.id.content, profileFrament)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }
}
