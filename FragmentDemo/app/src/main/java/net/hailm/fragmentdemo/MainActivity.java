package net.hailm.fragmentdemo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.hailm.fragmentdemo.fragment.EditorFragment;
import net.hailm.fragmentdemo.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    private ProfileFragment profileFrament;
    private EditorFragment editorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showProfileFragmentScreen();
    }


    public void showProfileFragmentScreen() {
       if (profileFrament ==null){
           profileFrament = new ProfileFragment();
       }

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(android.R.id.content, profileFrament)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
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
}
