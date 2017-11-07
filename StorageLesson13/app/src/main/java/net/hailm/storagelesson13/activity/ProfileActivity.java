package net.hailm.storagelesson13.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import net.hailm.storagelesson13.R;
import net.hailm.storagelesson13.fragment.ProfileFragment;

/**
 * Created by hai_l on 04/11/2017.
 */

public class ProfileActivity extends AppCompatActivity {
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeComponents();
    }

    // View mac dinh activity có id là android.R.id.content
    private void initializeComponents() {
        showProfileScreen();
    }

    private void showProfileScreen() {
        // Cần để có đối tượng để quản lý các Fragment
        // FragmentManager

        profileFragment = new ProfileFragment();
        // đnag  dùng support v4
        FragmentManager manager = getSupportFragmentManager();

//        manager.beginTransaction().add(android.R.id.content, profileFragment).commit();
        manager.beginTransaction()
                .replace(android.R.id.content, profileFragment)
                //.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();


    }
}
