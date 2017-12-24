package net.hailm.sqlitedemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import net.hailm.sqlitedemo.R;
import net.hailm.sqlitedemo.manager.DatabaseManager;
import net.hailm.sqlitedemo.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "data";
    private DatabaseManager databaseManager;
    private List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents() {
        databaseManager = new DatabaseManager(this);
        questions = new ArrayList<>();

        questions.addAll(databaseManager.getQuestions());

        Log.d(TAG, "Test: " + questions);
    }
}
