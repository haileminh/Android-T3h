package net.hailm.fragmentls14.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import net.hailm.fragmentls14.flagment.EditorFragment;
import net.hailm.fragmentls14.flagment.ProfileFragment;
import net.hailm.fragmentls14.model.Account;

/**
 * Created by hai_l on 14/11/2017.
 */

public class ProfileActivity extends AppCompatActivity {
    private ProfileFragment profileFragment;
    private EditorFragment editorFragment;
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeComponents();
    }

    private void initializeComponents() {
        //Cách 1:
//        showProfileFragmentScreen();

        // Cách 2:
        addFragment();
        showProfileScreenV2();
    }


    private void showProfileFragmentScreen() {
        // Cần để có các đối tượng quản lý các Fragment
        profileFragment = new ProfileFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(android.R.id.content, profileFragment)
                .commit();
    }

    public void showEditorFragmentScreen() {
        editorFragment = new EditorFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(android.R.id.content, editorFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }


    // Cách 2: Sd hide, show
    private void addFragment() {
        profileFragment = new ProfileFragment();
        editorFragment = new EditorFragment();

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(android.R.id.content, profileFragment)
                .add(android.R.id.content, editorFragment)
                .commit();

    }

    public void showProfileScreenV2() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .show(profileFragment)
                .hide(editorFragment)
                .commit();
    }

    public void showEditorScreenV2() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .show(editorFragment)
                .hide(profileFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (editorFragment.isVisible()) {
            showProfileScreenV2();
        } else {
            super.onBackPressed();
        }
    }
}
