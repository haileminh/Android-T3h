package com.phamhoan.navigationbar;


import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phamhoan.navigationbar.constant.Key;
import com.phamhoan.navigationbar.fragment.AddSanphamFragment;
import com.phamhoan.navigationbar.fragment.AndroidFragment;
import com.phamhoan.navigationbar.fragment.AsynctaskFragment;
import com.phamhoan.navigationbar.fragment.DangkyFragment;
import com.phamhoan.navigationbar.fragment.DanhSachFragment;
import com.phamhoan.navigationbar.fragment.HandlerFragment;
import com.phamhoan.navigationbar.fragment.MenuFragment;
import com.phamhoan.navigationbar.fragment.ServiceBroadcastFrament;
import com.phamhoan.navigationbar.fragment.ThreadFragment;
import com.phamhoan.navigationbar.fragment.ViewpagerTablayoutFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private ImageView imgMenu, imgSort;
    private DrawerLayout drawerLayout;
    private MenuFragment menuFragment;
    private LinearLayout lnlDangky, lnlThemsp, lnlDS, lnlService, lnlAsyn, lnlHandler, lnlThread, lnlView;
    private TextView txtTile;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int total;


    private String id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializce();
        restoringPreferences();
        Log.d(TAG, "callFragment: " + id);
    }

    private void initializce() {
        imgSort = findViewById(R.id.img_sort);
        imgMenu = findViewById(R.id.img_menu);
        drawerLayout = findViewById(R.id.dr_layout);
        txtTile = findViewById(R.id.txt_title);
        lnlDangky = findViewById(R.id.lnl_dangky);
        lnlThemsp = findViewById(R.id.lnl_themsp);
        lnlDS = findViewById(R.id.lnl_dssanpham);
        lnlService = findViewById(R.id.lnl_service);
        lnlAsyn = findViewById(R.id.lnl_asynctask);
        lnlHandler = findViewById(R.id.lnl_handler);
        lnlThread = findViewById(R.id.lnl_thread);
        lnlView = findViewById(R.id.lnl_view);

        imgSort.setOnClickListener(this);
        lnlDangky.setOnClickListener(this);
        imgMenu.setOnClickListener(this);
        lnlThemsp.setOnClickListener(this);
        lnlDS.setOnClickListener(this);
        lnlService.setOnClickListener(this);
        lnlAsyn.setOnClickListener(this);
        lnlHandler.setOnClickListener(this);
        lnlThread.setOnClickListener(this);
        lnlView.setOnClickListener(this);

        menuFragment = new MenuFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.lnl_show, menuFragment)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_sort:
                TestFragment();
                break;
            case R.id.img_menu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.lnl_dangky:
                callFragment(Key.DANGKY);
                drawerLayout.closeDrawers();
                break;
            case R.id.lnl_themsp:
                callFragment(Key.ADD_SAN_PHAM);
                drawerLayout.closeDrawers();
                break;
            case R.id.lnl_dssanpham:
                callFragment(Key.DANHSACH);
                drawerLayout.closeDrawers();
                break;
            case R.id.lnl_service:
                callFragment(Key.SERVICE_BROADCAST);
                drawerLayout.closeDrawers();
                break;
            case R.id.lnl_asynctask:
                callFragment(Key.ASYNCTASK);
                drawerLayout.closeDrawers();
                break;
            case R.id.lnl_handler:
                callFragment(Key.HANDLER);
                drawerLayout.closeDrawers();
                break;
            case R.id.lnl_thread:
                callFragment(Key.THREAD);
                drawerLayout.closeDrawers();
                break;
            case R.id.lnl_view:
                callFragment(Key.VIEWPAGER_TABLAYOUT);
                drawerLayout.closeDrawers();
                break;

            default:
                break;
        }
    }

    private void TestFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DanhSachFragment danhSachFragment = (DanhSachFragment) fragmentManager.findFragmentByTag(Key.DANHSACH);
        if(danhSachFragment != null){
            danhSachFragment.sortData();

        }


    }

    public void callFragment(String fragmentString) {
        Fragment fragment = null;
        String key="";
        switch (fragmentString) {
            case Key.MENU:
                fragment = new MenuFragment();
                break;
            case Key.ADD_SAN_PHAM:
                fragment = new AddSanphamFragment();
                break;
            case Key.ANDROID:
                fragment = new AndroidFragment();
                break;
            case Key.ASYNCTASK:
                fragment = new AsynctaskFragment();
                break;
            case Key.DANGKY:
                fragment = new DangkyFragment();
                break;
            case Key.DANHSACH:
                fragment = new DanhSachFragment();
                key = Key.DANHSACH;
                break;
            case Key.HANDLER:
                fragment = new HandlerFragment();
                break;
            case Key.SERVICE_BROADCAST:
                fragment = new ServiceBroadcastFrament();
                break;
            case Key.THREAD:
                fragment = new ThreadFragment();
                break;
            case Key.VIEWPAGER_TABLAYOUT:
                fragment = new ViewpagerTablayoutFragment();
                break;
            default:
                fragment = new MenuFragment();
                break;
        }
        if (fragment != null) {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.lnl_show, fragment,key)
                    .addToBackStack(null)
                    .commit();

        }

    }

    @Override
    protected void onPause() {
        savingPreferences();
        super.onPause();

    }

    private void restoringPreferences() {
        SharedPreferences pre = getSharedPreferences("account", MODE_PRIVATE);
        if (pre.getBoolean("check", false)) {
            id = pre.getString("ID", "");
        }

    }

    private void savingPreferences() {
        if (id != null) {
            SharedPreferences pre = getSharedPreferences("account", MODE_PRIVATE);
            SharedPreferences.Editor editor = pre.edit();
            editor.putString("ID", id);
            editor.putBoolean("check", true);
            editor.commit();

        }
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


}
