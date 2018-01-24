package com.phamhoan.navigationbar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.phamhoan.navigationbar.MainActivity;
import com.phamhoan.navigationbar.R;
import com.phamhoan.navigationbar.constant.Key;

/**
 * Created by PHAMHOAN on 12/10/2017.
 */

public class MenuFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private LinearLayout lnlDangky, lnlAddsanpham, lnlDanhsach, lnlService,
            lnlAsynctask, lnlHandler, lnlThread, lnlViewpager, lnlAndroid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {
        lnlDangky = rootView.findViewById(R.id.lnl_dangky);
        lnlAddsanpham = rootView.findViewById(R.id.lnl_addSanPham);
        lnlDanhsach = rootView.findViewById(R.id.lnl_danhsach);
        lnlService = rootView.findViewById(R.id.lnl_service);
        lnlAsynctask = rootView.findViewById(R.id.lnl_asynctask);
        lnlHandler = rootView.findViewById(R.id.lnl_handler);
        lnlThread = rootView.findViewById(R.id.lnl_thread);
        lnlViewpager = rootView.findViewById(R.id.lnl_viewpager);
        lnlAndroid = rootView.findViewById(R.id.lnl_android);

        lnlDangky.setOnClickListener(this);
        lnlAddsanpham.setOnClickListener(this);
        lnlDanhsach.setOnClickListener(this);
        lnlService.setOnClickListener(this);
        lnlAsynctask.setOnClickListener(this);
        lnlHandler.setOnClickListener(this);
        lnlThread.setOnClickListener(this);
        lnlViewpager.setOnClickListener(this);
        lnlAndroid.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lnl_dangky:
                ((MainActivity) getActivity()).callFragment(Key.DANGKY);
                break;
            case R.id.lnl_addSanPham:
                ((MainActivity) getActivity()).callFragment(Key.ADD_SAN_PHAM);
                break;
            case R.id.lnl_android:
                ((MainActivity) getActivity()).callFragment(Key.ANDROID);
                break;
            case R.id.lnl_asynctask:
                ((MainActivity) getActivity()).callFragment(Key.ASYNCTASK);
                break;
            case R.id.lnl_danhsach:
                ((MainActivity) getActivity()).callFragment(Key.DANHSACH);
                break;
            case R.id.lnl_handler:
                ((MainActivity) getActivity()).callFragment(Key.HANDLER);
                break;
            case R.id.lnl_service:
                ((MainActivity) getActivity()).callFragment(Key.SERVICE_BROADCAST);
                break;
            case R.id.lnl_thread:
                ((MainActivity) getActivity()).callFragment(Key.THREAD);
                break;
            case R.id.lnl_viewpager:
                ((MainActivity) getActivity()).callFragment(Key.VIEWPAGER_TABLAYOUT);
                break;
            default:
                break;
        }
    }
}
