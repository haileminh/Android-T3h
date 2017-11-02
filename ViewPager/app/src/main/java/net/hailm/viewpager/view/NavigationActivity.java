package net.hailm.viewpager.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import net.hailm.viewpager.R;

/**
 * Created by hai_l on 01/11/2017.
 */

public class NavigationActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    private ImageView imgMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        initializeComponents();
        registerListener();
    }


    private void initializeComponents() {
        drawerLayout = findViewById(R.id.drawerlayout);
        imgMenu = findViewById(R.id.img_menu);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private void registerListener() {
        imgMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_menu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;

            default:
                break;
        }

    }
}
