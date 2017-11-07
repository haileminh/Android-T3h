package net.hailm.storagelesson13.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.hailm.storagelesson13.R;

import org.w3c.dom.Text;

/**
 * Created by hai_l on 04/11/2017.
 */

public class ProfileFragment extends Fragment {
    private View rootView;
    private TextView txtName;
    private TextView txtEmail;
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
    }

    private void initializeComponents() {
        txtName = rootView.findViewById(R.id.txt_name);
    }
}
