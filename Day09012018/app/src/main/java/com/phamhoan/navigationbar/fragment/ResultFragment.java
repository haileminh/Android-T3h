package com.phamhoan.navigationbar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phamhoan.navigationbar.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by PHAMHOAN on 12/20/2017.
 */

public class ResultFragment extends Fragment {
    View rootView;
    private TextView txtID, txtName, txtCategory, txtDate, txtUserID;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {
        txtID = rootView.findViewById(R.id.txt_ID);
        txtName = rootView.findViewById(R.id.txt_productName);
        txtCategory = rootView.findViewById(R.id.txt_productCategory);
        txtDate = rootView.findViewById(R.id.txt_Date);
        txtUserID = rootView.findViewById(R.id.txt_UserID);

        Bundle bundle = getArguments();
        txtName.setText("Name: " + bundle.getString("Name"));
        txtCategory.setText("Category: " + bundle.getString("Category"));
        txtDate.setText("CreatedDate: " + coverDate(bundle.getString("Date")));
        txtID.setText("ID: " + bundle.getInt("ID"));
        txtUserID.setText("UserID: " + bundle.getInt("UserID"));

    }

    private String coverDate(String unix) {
        long unix1 = Long.parseLong(unix);
        Date date = new Date(unix1 * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy ");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

}
