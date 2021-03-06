package com.phamhoan.navigationbar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.phamhoan.navigationbar.R;


public class FragmentOne extends Fragment {
    View rootView;
    private TextView txtCount;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_one, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        txtCount = rootView.findViewById(R.id.txt_count1);
        setCount(0);

    }
    public void setCount(int a){
        txtCount.setText(a+"");
    }

}
