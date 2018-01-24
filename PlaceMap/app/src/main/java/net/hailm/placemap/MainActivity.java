package net.hailm.placemap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.hailm.placemap.constant.Key;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private EditText edtSearch;
    private ImageView imgMenu, imgSearch;
    private DrawerLayout drawerLayout;
    private MenuFragment menuFragment;
    private LinearLayout lnlDangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents() {
        imgMenu = findViewById(R.id.img_menu);
        imgSearch = findViewById(R.id.img_search);
        edtSearch = findViewById(R.id.edt_search);
        drawerLayout = findViewById(R.id.dr_layout);
        lnlDangky = findViewById(R.id.lnl_dangky);


        imgSearch.setOnClickListener(this);
        lnlDangky.setOnClickListener(this);
        imgMenu.setOnClickListener(this);

        initMainFragmain();
    }

    private void initMainFragmain() {
        menuFragment = new MenuFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.lnl_show, menuFragment)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_menu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.img_search:
                break;
            case R.id.lnl_dangky:
                callFragment(Key.DANGKY);
                drawerLayout.closeDrawers();
                break;

            default:
                break;
        }
    }

    public void callFragment(String fragmentString) {
        Fragment fragment = null;
        String key = "";
        switch (fragmentString) {
            case Key.MENU:
                fragment = new MenuFragment();
                break;
            case Key.DANGKY:
//                fragment = new DangkyFragment();
                break;
            default:
                fragment = new MenuFragment();
                break;
        }

        if (fragment != null) {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.lnl_show, fragment, key)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
