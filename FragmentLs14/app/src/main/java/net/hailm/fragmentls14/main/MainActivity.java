package net.hailm.fragmentls14.main;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import net.hailm.fragmentls14.R;
import net.hailm.fragmentls14.flagment.EditorFragment;
import net.hailm.fragmentls14.flagment.HomeFragment;
import net.hailm.fragmentls14.flagment.ProfileFragment;
import net.hailm.fragmentls14.flagment.SearchFragment;
import net.hailm.fragmentls14.flagment.UserFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView btnHome, btnSearch, btnLike, btnAdd, btnUser;

    private HomeFragment homeFragment;
    private ProfileFragment profileFragment;
    private EditorFragment editorFragment;
    private SearchFragment searchFragment;
    private UserFragment userFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        registerListener();
    }


    private void initializeComponents() {
        btnHome = findViewById(R.id.btn_home);
        btnAdd = findViewById(R.id.btn_add);
        btnSearch = findViewById(R.id.btn_search);
        btnLike = findViewById(R.id.btn_like);
        btnUser = findViewById(R.id.btn_user);

        addFragment();
        showHomeFragment();
    }

    private void registerListener() {
        btnHome.setOnClickListener(this);
        btnLike.setOnClickListener(this);
        btnUser.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_home:
                showHomeFragment();
                break;

            case R.id.btn_search:
                //showEditorFragment();
                break;

            case R.id.btn_add:
                showSearhFragment();
                break;

            case R.id.btn_like:
                break;

            case R.id.btn_user:
                showUserFragment();
                break;

            default:
                break;
        }
    }

    private void addFragment() {
        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment();
        editorFragment = new EditorFragment();
        userFragment = new UserFragment();
        searchFragment = new SearchFragment();

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.frl_contents, homeFragment)
                .add(R.id.frl_contents, searchFragment)
                .add(R.id.frl_contents, userFragment)
//                .add(R.id.frl_contents, editorFragment)
                .commit();
    }

    public void showHomeFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .show(homeFragment)
                .hide(userFragment)
                .hide(searchFragment)
                .commit();
    }

    public void showSearhFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .show(searchFragment)
                .hide(homeFragment)
                .hide(userFragment)
                .addToBackStack(null)
                .commit();
    }

    public void showUserFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .show(userFragment)
                .hide(searchFragment)
                .hide(homeFragment)
                .addToBackStack(null)
                .commit();
    }


}
