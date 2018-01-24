package net.hailm.fragmentdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.hailm.fragmentdemo.R;

/**
 * Created by hai_l on 21/01/2018.
 */

public class TestFragment extends AppCompatActivity {
    private FirstFragment mFirstFragment;
    private SecondFragment mSecondFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initFragment();
        showFirstFragment();
    }


    private void initFragment() {
        mFirstFragment = new FirstFragment();
        mFirstFragment.setListener(new FirstFragment.OnClickButtonListener() {
            @Override
            public void clickButton() {
                showSecondFragment();
            }
        });
        mSecondFragment = new SecondFragment();
        mSecondFragment.setListener(new SecondFragment.OnClickButtonListener() {
            @Override
            public void clickButton() {
                showFirstFragment();
            }
        });
        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, mFirstFragment, FirstFragment.class.getName())
                .add(android.R.id.content, mSecondFragment, FirstFragment.class.getName())
                .commit();
    }

    private void showFirstFragment() {
        getSupportFragmentManager().beginTransaction()
                .show(mFirstFragment)
                .hide(mSecondFragment)
                .commit();
    }

    private void showSecondFragment() {
        getSupportFragmentManager().beginTransaction()
                .show(mSecondFragment)
                .hide(mFirstFragment)
                .addToBackStack(null)
                .commit();
    }
}
