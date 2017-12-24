package net.hailm.fragmentls14.flagment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.hailm.fragmentls14.R;
import net.hailm.fragmentls14.main.ProfileActivity;
import net.hailm.fragmentls14.model.Account;

/**
 * Created by hai_l on 14/11/2017.
 */

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private TextView txtName, txtEmail;
    private Button btnEditProfile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeComponents();

        Account account = ((ProfileActivity) getActivity()).getAccount();
        if (account != null) {
            txtName.setText(account.getName());
            txtEmail.setText(account.getEmail());

        }
    }

    private void initializeComponents() {
        txtName = rootView.findViewById(R.id.txt_name);
        txtEmail = rootView.findViewById(R.id.txt_email);
        btnEditProfile = rootView.findViewById(R.id.btn_edit_profile);

        btnEditProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit_profile:
                //Cách 1:
//                ((ProfileActivity) getActivity()).showEditorFragmentScreen();

                // Cách 2:
                ((ProfileActivity) getActivity()).showEditorScreenV2();
                break;
            default:
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {

            Account account = ((ProfileActivity) getActivity()).getAccount();
            if (account != null) {
                txtName.setText(account.getName());
                txtEmail.setText(account.getEmail());
            }
        }
    }
}
