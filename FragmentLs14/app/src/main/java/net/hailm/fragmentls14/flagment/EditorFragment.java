package net.hailm.fragmentls14.flagment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.hailm.fragmentls14.R;
import net.hailm.fragmentls14.main.ProfileActivity;
import net.hailm.fragmentls14.model.Account;

/**
 * Created by hai_l on 14/11/2017.
 */

public class EditorFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private EditText edtName, edtEmail;
    private Button btnSubmit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragmnet_editor, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponents();
    }

    private void initializeComponents() {
        edtEmail = rootView.findViewById(R.id.edt_email);
        edtName = rootView.findViewById(R.id.edt_name);
        btnSubmit = rootView.findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                updateProfile();
                break;

            default:
                break;
        }
    }

    private void updateProfile() {
        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();

        if (name.isEmpty() || email.isEmpty()) {
            // sd gói sp.v4 ms dùng được getContext
            Toast.makeText(getContext(), "Please enter values", Toast.LENGTH_SHORT).show();
            return;
        }

        Account account = new Account(name, email);
        ((ProfileActivity) getActivity()).setAccount(account);

        getActivity().onBackPressed();
    }
}
